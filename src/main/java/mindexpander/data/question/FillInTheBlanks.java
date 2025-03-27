package mindexpander.data.question;

public class FillInTheBlanks extends Question {
    public FillInTheBlanks(String question, String answer) {
        super(question, answer, QuestionType.FITB);
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return answer.equalsIgnoreCase(userAnswer.trim());
    }

    public void showQuestion() {
        System.out.println("FITB: " + question);
    }

    @Override
    public String toString() {
        return "FITB: " + question + " [Answer: " + answer + "]";
    }

    @Override
    public String toStringNoAnswer() {
        return "FITB: " + question;
    }
}
