package mindexpander.commands;

import mindexpander.data.QuestionBank;
import mindexpander.data.question.MultipleChoice;
import mindexpander.data.question.Question;
import mindexpander.data.question.QuestionType;
import mindexpander.exceptions.IllegalCommandException;

import static java.lang.Integer.parseInt;

/**
 * Represents a command to edit a specific question from the question bank.
 * This command supports editing the question text, answer, or individual options
 * for multiple choice questions, and is designed as a multistep command.
 */
public class EditCommand extends Command implements Multistep {
    private final int indexToEdit;
    private String editedAttribute;
    private int optionIndex = 0;
    private final Question questionToEdit;
    private final QuestionBank mainBank;
    private final QuestionBank lastShownBank;

    public EditCommand(int indexToEdit, String toEdit, QuestionBank mainBank, QuestionBank lastShownBank) {
        this.indexToEdit = indexToEdit - 1;
        this.questionToEdit = lastShownBank.getQuestion(this.indexToEdit);
        this.mainBank = mainBank;
        this.lastShownBank = lastShownBank;
        isComplete = false;
        updateCommandMessage(initialiseEditCommandMessage(toEdit));
    }

    private String initialiseEditCommandMessage(String toEdit) {
        if (!(toEdit.equals("q") || toEdit.equals("a") || toEdit.equals("o"))) {
            isComplete = true;
            return "Invalid format. Please use edit [QUESTION_IDEX] [q/a/o]" +
                    "\n" + "'q' - question content" +
                    "\n" + "'a' - answer" +
                    "\n" + "'o' for multiple choice options.";
        }
        if (toEdit.equals("q")) {
            this.editedAttribute = "question";
            return String.format("Editing %1$s \n Please enter the new question:", questionToEdit);
        }
        if (toEdit.equals("a")) {
            this.editedAttribute = "answer";
            return String.format("Editing %1$s \n Please enter the new answer:", questionToEdit);
        }
        if (questionToEdit instanceof MultipleChoice mcq) {
            this.editedAttribute = "option";
            return String.format("Editing %1$s \n Please enter the index of the option you want to edit: \n%2$s",
                    questionToEdit, mcq.getIncorrectOptions());
        }
        isComplete = true;
        return "Only multiple choice questions have options to modify.";
    }

    /**
     * Handles user input for each step of the editing process.
     *
     * @param nextInput the new input to apply to the specified field.
     * @param questionBank the {@code QuestionBank} used for reference.
     * @return the updated {@code Command} instance.
     */
    @Override
    public Command handleMultistepCommand(String nextInput, QuestionBank questionBank) {
        assert lastShownBank != null : "lastShownBank must not be null";

        if (nextInput.trim().isEmpty()) {
            updateCommandMessage(String.format("%1$s cannot be empty!", editedAttribute));
            return this;
        }

        int targetIndex = mainBank.findQuestionIndex(lastShownBank.getQuestion(indexToEdit));

        if (targetIndex == -1) {
            throw new IllegalCommandException("Unable to find question in main bank to delete.");
        }

        if (editedAttribute.equals("question")) {
            return editQuestion(targetIndex, nextInput);
        }

        if (editedAttribute.equals("answer")) {
            return editAnswer(targetIndex, nextInput);
        }

        if (!(optionIndex > 0 && optionIndex <= 3)) {
            try {
                this.optionIndex = parseInt(nextInput);
                if (optionIndex > 3 || optionIndex < 1) {
                    updateCommandMessage("Please enter a valid index from 1 to 3.");
                    return this;
                }
                updateCommandMessage("Please enter the new option");
                return this;
            } catch (NumberFormatException e) {
                updateCommandMessage("Please enter a valid index from 1 to 3.");
                return this;
            }
        }

        return editOptions(targetIndex, optionIndex, nextInput);

    }

    /**
     * Applies the new question text to the specified question.
     *
     * @param targetIndex the index in the main question bank.
     * @param nextInput the new question text.
     * @return the updated {@code Command} instance.
     */
    public Command editQuestion(int targetIndex, String nextInput) {
        for (Question q : mainBank.getAllQuestions()) {
            if (q.getQuestion().equals(nextInput)) {
                updateCommandMessage("Question already exists. Terminating edit command.");
                isComplete = true;
                return this;
            }
        }
        mainBank.getQuestion(targetIndex).editQuestion(nextInput);
        updateCommandMessage(String.format("Question successfully edited: %1$s", mainBank.getQuestion(targetIndex)));
        isComplete = true;
        return this;
    }

    /**
     * Applies the new answer to the specified question.
     * Special validation is applied for True/False questions.
     *
     * @param targetIndex the index in the main question bank.
     * @param nextInput the new answer input.
     * @return the updated {@code Command} instance.
     */
    public Command editAnswer(int targetIndex, String nextInput) {
        Question q = mainBank.getQuestion(targetIndex);

        if (q instanceof MultipleChoice mcq) {
            if (mcq.getOptions().contains(nextInput)) {
                updateCommandMessage("Invalid input: Duplicate options are not allowed in multiple choice questions.");
                return this;
            }
        }

        if (q.getType().equals(QuestionType.TF)) {
            String trimmedLower = nextInput.trim().toLowerCase();
            if (!trimmedLower.equals("true") && !trimmedLower.equals("false")) {
                updateCommandMessage("Invalid answer for True/False question. Please enter 'true' or 'false'.");
                return this;
            }
            q.editAnswer(trimmedLower);
        } else {
            q.editAnswer(nextInput);
        }

        updateCommandMessage(String.format("Question successfully edited: %1$s", mainBank.getQuestion(targetIndex)));
        isComplete = true;
        return this;
    }

    /**
     * Updates one of the options in a multiple choice question.
     *
     * @param targetIndex the index in the main question bank.
     * @param optionIndex the index in the target option to be edited.
     * @param nextInput the new option text.
     * @return the updated {@code Command} instance.
     */
    public Command editOptions(int targetIndex, int optionIndex, String nextInput) {
        Question question = mainBank.getQuestion(targetIndex);
        if (question instanceof MultipleChoice mcq) {
            if (mcq.getOptions().contains(nextInput)) {
                updateCommandMessage("Invalid input: Duplicate options are not allowed in multiple choice questions.");
                return this;
            }
            mcq.editOption(optionIndex, nextInput);
        }
        updateCommandMessage(String.format("Question successfully edited: %1$s",
                mainBank.getQuestion(targetIndex).toStringNoAnswer()));
        isComplete = true;
        return this;
    }
}
