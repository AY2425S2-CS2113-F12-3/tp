package mindexpander.commands;

import mindexpander.data.question.Question;

import mindexpander.data.QuestionBank;

public class SolveCommand extends Command {
    private enum Step { GET_INDEX, GET_ANSWER }
    private Step currentStep = Step.GET_INDEX;
    private int questionIndex = -1;

    public SolveCommand() {
        isComplete = false; // Multistep command
        updateCommandMessage("Please enter the question number you would like to solve.");
    }

    @Override
    public Command handleMultistepCommand(String nextInput, QuestionBank questionBank) {
        switch (currentStep) {
        case GET_INDEX:
            updateCommandMessage(getQuestionIndex(nextInput, questionBank));
            return this; // Stay in SolveCommand

        case GET_ANSWER:
            Question question = questionBank.getQuestion(questionIndex);
            String correctAnswer = question.getAnswer();

            if (!nextInput.trim().equalsIgnoreCase(correctAnswer.trim())) {
                updateCommandMessage("Wrong answer, please try again.");
                return this; // Try again
            }
            updateCommandMessage("Correct!");
            isComplete = true;
            return this; // Exit multi-step mode

        default:
            return this; // Default return, should not reach here
        }
    }

    private String getQuestionIndex(String nextInput, QuestionBank questionBank) {
        try {
            questionIndex = Integer.parseInt(nextInput) - 1; // Convert to 0-based index

            if (questionIndex < 0 || questionIndex >= questionBank.getQuestionCount()) {
                return "Invalid question number. Please enter a valid index.";
            }

            currentStep = Step.GET_ANSWER;
            return "Attempting question " + (questionIndex + 1) + ", enter your answer:";
        } catch (NumberFormatException e) {
            return "Invalid input. Please enter a number." ;
        }
    }
}
