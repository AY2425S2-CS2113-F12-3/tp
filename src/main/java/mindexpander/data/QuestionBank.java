package mindexpander.data;

import mindexpander.data.question.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {
    private final List<Question> allQuestions;

    public QuestionBank() {
        this.allQuestions = new ArrayList<>();
    }

    public QuestionBank(List<Question> allQuestions) {
        this.allQuestions = allQuestions;
    }
    
    public void addQuestion(Question toAdd) {
        allQuestions.add(toAdd);
    }

    public void removeQuestion(int index) {
        allQuestions.remove(index);
    }

    public int getQuestionCount() {
        return allQuestions.size();
    }

    public List<Question> getAllQuestions() {
        return this.allQuestions;
    }

    public Question getQuestion(int index) {
        return allQuestions.get(index);
    }

    public int findQuestionIndex(Question q) {
        for (int i = 0; i < allQuestions.size(); i++) {
            if (allQuestions.get(i).equals(q)) {
                return i;
            }
        }
        return -1; // not found
    }

    public boolean isEmpty() {
        return getQuestionCount() == 0;
    }
}
