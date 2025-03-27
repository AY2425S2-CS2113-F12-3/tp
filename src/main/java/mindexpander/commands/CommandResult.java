package mindexpander.commands;

import mindexpander.data.QuestionBank;

public class CommandResult {
    public final String commandResultToUser;
    public final QuestionBank questionBank;
    public final boolean showAnswer;

    public CommandResult(String commandResultToUser, QuestionBank questionBank, Boolean showAnswer) {
        this.commandResultToUser = commandResultToUser;
        this.questionBank = questionBank;
        this.showAnswer = showAnswer;
    }

    public CommandResult(String commandResultToUser) {
        this.commandResultToUser = commandResultToUser;
        this.questionBank = null;
        this.showAnswer = false;
    }

    public QuestionBank getQuestionBank() {
        return questionBank;
    }
}
