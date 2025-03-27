package mindexpander.commands;

import mindexpander.data.QuestionBank;

/**
 * The {@code ListCommand} class updates display message
 * to show the list of questions saved in the user's device
 *
 * @author Kian Kiat
 * @version v0.2
 * @since 2025-03-11
 */
public class ListCommand extends Command {
    // Attributes
    private static final String LIST_COMMAND_MESSAGE = "Here are the questions you have currently: ";
    private final QuestionBank questionBank;
    private final boolean showAnswer;

    // Constructor
    public ListCommand(QuestionBank questionBank, Boolean showAnswer) {
        this.questionBank = questionBank;
        this.showAnswer = showAnswer;
        updateCommandMessage(LIST_COMMAND_MESSAGE);
    }

    // Methods
    @Override
    public CommandResult execute() {
        String messageToUser = questionBank.getQuestionCount() == 0
                ? "You have no questions yet!"
                : "Here are the questions you have stored:";
        return new CommandResult(messageToUser, questionBank, showAnswer);
    }
}
