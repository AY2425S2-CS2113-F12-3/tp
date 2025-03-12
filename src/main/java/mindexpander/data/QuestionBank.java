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

    public void list() {
        for (Question q : allQuestions) {
            q.showQuestion();
        }
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

    public Question getQuestion(int index) {
        return allQuestions.get(index);
    }
}
