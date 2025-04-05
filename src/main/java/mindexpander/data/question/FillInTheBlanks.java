package mindexpander.data.question;

/**
 * Represents a Fill-in-the-Blank type question.
 */
public class FillInTheBlanks extends Question {
    public FillInTheBlanks(String question, String answer) {
        super(question, answer, QuestionType.FITB);
    }

    /**
     * Checks if the user answer is correct (case-insensitive).
     *
     * @param userAnswer The user's answer.
     * @return true if correct, false otherwise.
     */
    @Override
    public boolean checkAnswer(String userAnswer) {
        return answer.equalsIgnoreCase(userAnswer.trim());
    }

    /**
     * Returns a string representation with the answer.
     *
     * @return The question and answer.
     */
    @Override
    public String toString() {
        return "FITB: " + question + " [Answer: " + answer + "]";
    }


    /**
     * Returns a string representation without the answer.
     *
     * @return The question only.
     */
    @Override
    public String toStringNoAnswer() {
        return "FITB: " + question;
    }
}
