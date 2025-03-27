package mindexpander.data.question;

public abstract class Question {
    protected String question;
    protected String answer;
    protected final QuestionType type;

    public Question(String question, String answer, QuestionType type) {
        this.question = question;
        this.answer = answer;
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public QuestionType getType() {
        return this.type;
    }

    public void editQuestion(String newQuestion) {
        this.question = newQuestion;
    }

    public void editAnswer(String newAnswer) {
        this.answer = newAnswer;
    }

    public abstract boolean checkAnswer(String input);

    public abstract void showQuestion();

    public abstract String toString();

    public abstract String toStringNoAnswer();
}
