package mindexpander.tests;

import mindexpander.commands.AddCommand;
import mindexpander.data.QuestionBank;
import mindexpander.data.question.Question;
import mindexpander.data.question.QuestionType;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCommandTest {
    @Test
    void testInitialState() {
        AddCommand addCommand = new AddCommand();
        assertFalse(addCommand.isCommandComplete());
        assertEquals("Please enter the type of the question you would like to add. (fitb/mcq/tf)",
                addCommand.getCommandMessage());
    }

    @Test
    public void addCommand_invalidType() {
        AddCommand addCommand = new AddCommand();
        QuestionBank questionBank = new QuestionBank();
        final String[] invalidTypes = {"abc", "[]\\[;]", "question"};
        for (String type : invalidTypes) {
            addCommand.handleMultistepCommand(type, questionBank);
            assertEquals("Invalid input. Please enter a correct question type. (fitb/mcq/tf)",
                    addCommand.getCommandMessage());
        }
    }

    @Test
    public void addCommand_emptyInput() {
        AddCommand addCommand = new AddCommand();
        QuestionBank questionBank = new QuestionBank();
        addCommand.handleMultistepCommand("fitb", questionBank);
        addCommand.handleMultistepCommand("", questionBank);
        assertEquals("Input cannot be empty!", addCommand.getCommandMessage());
    }

    @Test
    public void addCommand_validData_correctlyConstructed() {
        QuestionBank questionBank = new QuestionBank();
        AddCommand addCommand = new AddCommand();
        addCommand.handleMultistepCommand("fitb", questionBank);
        addCommand.handleMultistepCommand("What is 2 + 2?", questionBank);
        addCommand.handleMultistepCommand("4", questionBank);
        Question question = questionBank.getQuestion(0);

        assertTrue(addCommand.isCommandComplete());
        assertEquals(QuestionType.FITB, question.getType());
        assertEquals("What is 2 + 2?", question.getQuestion());
        assertEquals("4", question.getAnswer());
    }
}
