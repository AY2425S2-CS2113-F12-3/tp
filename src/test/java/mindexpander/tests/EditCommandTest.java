package mindexpander.tests;

import mindexpander.commands.EditCommand;
import mindexpander.data.QuestionBank;
import mindexpander.data.question.FillInTheBlanks;
import mindexpander.data.question.MultipleChoice;
import mindexpander.data.question.Question;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditCommandTest {
    @Test
    void testInitialState() {
        QuestionBank questionBank = new QuestionBank();
        QuestionBank lastShownQuestionBank = new QuestionBank();
        Question question = new FillInTheBlanks("test q", "test a");
        questionBank.addQuestion(question);
        lastShownQuestionBank.addQuestion(question);
        EditCommand editCommand = new EditCommand(1, "q", questionBank, lastShownQuestionBank);
        assertFalse(editCommand.isCommandComplete());
        assertEquals("Editing FITB: test q [Answer: test a] \n Please enter the new question:",
                editCommand.getCommandMessage());
    }

    @Test
    public void editCommand_correctlyEditsQuestion() {
        QuestionBank questionBank = new QuestionBank();
        QuestionBank lastShownQuestionBank = new QuestionBank();
        Question question = new FillInTheBlanks("test q", "test a");
        questionBank.addQuestion(question);
        lastShownQuestionBank.addQuestion(question);
        EditCommand editCommand = new EditCommand(1, "q", questionBank, lastShownQuestionBank);
        editCommand.handleMultistepCommand("new q", questionBank);

        assertTrue(editCommand.isCommandComplete());
        assertEquals("new q", question.getQuestion());
        assertEquals("test a", question.getAnswer());
    }

    @Test
    public void editCommand_correctlyEditsAnswer() {
        QuestionBank questionBank = new QuestionBank();
        QuestionBank lastShownQuestionBank = new QuestionBank();
        Question question = new FillInTheBlanks("test q", "test a");
        questionBank.addQuestion(question);
        lastShownQuestionBank.addQuestion(question);
        EditCommand editCommand = new EditCommand(1, "a", questionBank, lastShownQuestionBank);
        editCommand.handleMultistepCommand("new a", questionBank);

        assertTrue(editCommand.isCommandComplete());
        assertEquals("test q", question.getQuestion());
        assertEquals("new a", question.getAnswer());
    }

    @Test
    public void editCommand_correctlyEditsOption() {
        QuestionBank questionBank = new QuestionBank();
        QuestionBank lastShownQuestionBank = new QuestionBank();
        List<String> options = List.of("test a", "test 1", "test 2", "test 3");
        Question question = new MultipleChoice("test q", "test a", options);
        questionBank.addQuestion(question);
        lastShownQuestionBank.addQuestion(question);
        EditCommand editCommand = new EditCommand(1, "o", questionBank, lastShownQuestionBank);
        editCommand.handleMultistepCommand("1", questionBank);
        editCommand.handleMultistepCommand("new test", questionBank);

        assertTrue(editCommand.isCommandComplete());
        assertEquals("test q", question.getQuestion());
        assertEquals("test a", question.getAnswer());
    }
}
