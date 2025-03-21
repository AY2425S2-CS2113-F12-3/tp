package mindexpander.commands;

import mindexpander.data.question.Question;

import mindexpander.data.QuestionBank;

public class SolveCommand extends Command {
    private enum Step { GET_INDEX, GET_ANSWER, GET_TRY_AGAIN_RESPONSE }
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
                updateCommandMessage("Wrong answer, would you like to try again? [Y/N]");
                currentStep = Step.GET_TRY_AGAIN_RESPONSE;
                return this; // Try again
            }
            updateCommandMessage("Correct!");
            isComplete = true;
            return this; // Exit multi-step mode
        case GET_TRY_AGAIN_RESPONSE:
            if (nextInput.trim().equalsIgnoreCase("Y")) {
                updateCommandMessage("Enter your answer to try again: ");
                currentStep = Step.GET_ANSWER;
                return this; // Try again
            }
            if (nextInput.trim().equalsIgnoreCase("N")) {
                updateCommandMessage("Giving up on question.");
                isComplete = true;
                return this;
            }
            updateCommandMessage("Please enter Y or N.");
            return this;

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
            String questionDetails = questionBank.getQuestion(questionIndex).getQuestion();
            return "Attempting question " + (questionIndex + 1) + ": " + questionDetails + "\nEnter your answer:";
        } catch (NumberFormatException e) {
            return "Invalid input. Please enter a number." ;
        }
    }
}
