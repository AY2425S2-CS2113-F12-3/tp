package mindexpander.data.question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a Multiple-Choice question.
 */
public class MultipleChoice extends Question {
    private List<String> options;
    private String answerOption;

    public MultipleChoice(String question, String answer, List<String> options) {
        super(question, answer, QuestionType.MCQ);
        this.options = new ArrayList<>(options);
        this.answerOption = "A";
    }

    public List<String> getOptions() {
        return new ArrayList<>(options);
    }

    public String getIncorrectOptions() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 3; i += 1) {
            sb.append("  ").append(i).append(". ").append(options.get(i)).append("\n");
        }
        sb.setLength(Math.max(sb.length() - 1, 0));
        return sb.toString();
    }

    public void editOption(int index, String newOption) {
        options.set(index, newOption);
    }


    @Override
    public void editAnswer(String newAnswer) {
        this.answer = newAnswer;
        options.set(0, newAnswer);
    }

    /**
     * Checks if the user's answer matches the correct option letter.
     *
     * @param userAnswer The user's selected option (e.g., A, B, C).
     * @return true if correct, false otherwise.
     */
    @Override
    public boolean checkAnswer(String userAnswer) {
        return answerOption.equalsIgnoreCase(userAnswer.trim());
    }

    /**
     * Returns a string representation with the answer.
     *
     * @return The question and answer.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MCQ: ").append(question).append(" [Answer: ").append(answer).append("]");
        return sb.toString();
    }


    /**
     * Returns a shuffled version of the question and options without showing the answer.
     *
     * @return The question and options.
     */
    @Override
    public String toStringNoAnswer() {
        List<String> shuffledOptions = new ArrayList<>(options);
        Collections.shuffle(shuffledOptions);
        updateAnswerOption(shuffledOptions);
        StringBuilder sb = new StringBuilder();
        sb.append("MCQ: ").append(question).append("\n");
        for (int i = 0; i < shuffledOptions.size(); i += 1) {
            sb.append("  ").append((char) ('A' + i)).append(". ").append(shuffledOptions.get(i)).append("\n");
        }
        sb.setLength(Math.max(sb.length() - 1, 0));
        return sb.toString();
    }

    /**
     * Updates the correct option letter after shuffling.
     *
     * @param shuffledOptions The shuffled options list.
     */
    private void updateAnswerOption(List<String> shuffledOptions) {
        for (int i = 0; i < 4; i += 1) {
            if (shuffledOptions.get(i).equals(answer)) {
                answerOption = String.valueOf((char) ('A' + i));
                return;
            }
        }
    }
}
