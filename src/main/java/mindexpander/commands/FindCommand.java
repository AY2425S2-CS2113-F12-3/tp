package mindexpander.commands;

import mindexpander.data.QuestionBank;

public class FindCommand extends Command {
    // Attributes
    private final QuestionBank questionBank;
    private final String questionType;
    private final String keyword;

    // Constructor
    public FindCommand(QuestionBank questionBank, String questionType, String keyword) {
        this.questionBank = questionBank;
        this.questionType = questionType.toLowerCase();
        this.keyword = keyword;
    }

    // Methods
    @Override
    public CommandResult execute() {
        QuestionBank filteredQuestionBank = new QuestionBank();
        for (int i = 0; i < questionBank.getQuestionCount(); i++) {
            boolean matchQuestionType = questionType.equals("all")
                    || questionBank.getQuestion(i).getType().getType().equalsIgnoreCase(questionType);
            boolean containsKeyword = questionBank.getQuestion(i).getQuestion().toLowerCase()
                    .contains(keyword.toLowerCase());
            if (matchQuestionType && containsKeyword) {
                filteredQuestionBank.addQuestion(questionBank.getQuestion(i));
            }
        }
        String messageToUser = filteredQuestionBank.getQuestionCount() == 0
                ? "No questions with " + keyword + " found!"
                : "Here are the questions with " + keyword + ":";
        return new CommandResult(messageToUser, filteredQuestionBank, false);
    }
}
