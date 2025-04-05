package mindexpander.data.question;

/**
 * Represents an abstract Question with a question text, an answer, and a type.
 */
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

    /**
     * Checks if the user's input is correct.
     *
     * @param input The user's answer.
     * @return true if correct, false otherwise.
     */
    public abstract boolean checkAnswer(String input);

    /**
     * Returns a string representation of the question with the answer.
     *
     * @return String with answer.
     */
    public abstract String toString();

    /**
     * Returns a string representation of the question without the answer.
     *
     * @return String without answer.
     */
    public abstract String toStringNoAnswer();
}
