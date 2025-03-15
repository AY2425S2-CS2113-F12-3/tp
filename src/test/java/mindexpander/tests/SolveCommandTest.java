package mindexpander.tests;

import mindexpander.commands.SolveCommand;
import mindexpander.data.QuestionBank;
import mindexpander.data.question.FillInTheBlanks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * JUnit test class for {@code SolveCommand}.
 *
 * @author Wenyi
 * @version 1.0
 * @since 2025-02-15
 */
class SolveCommandTest {
    private QuestionBank questionBank;
    private SolveCommand solveCommand;

    @BeforeEach
    void setUp() {
        questionBank = new QuestionBank();
        questionBank.addQuestion(new FillInTheBlanks("The capital of Japan is ___", "Tokyo"));
        questionBank.addQuestion(new FillInTheBlanks("Water boils at ___ degrees Celsius.", "100"));
        solveCommand = new SolveCommand();
    }

    @Test
    void testInitialState() {
        assertFalse(solveCommand.isCommandComplete());
        assertEquals("Please enter the question number you would like to solve.", solveCommand.getCommandMessage());
    }

    @Test
    void testValidQuestionIndex() {
        solveCommand = (SolveCommand) solveCommand.handleMultistepCommand("1", questionBank);
        assertEquals("Attempting question 1, enter your answer:", solveCommand.getCommandMessage());
        assertFalse(solveCommand.isCommandComplete());
    }

    @Test
    void testInvalidQuestionIndex() {
        solveCommand = (SolveCommand) solveCommand.handleMultistepCommand("99", questionBank);
        assertEquals("Invalid question number. Please enter a valid index.", solveCommand.getCommandMessage());
        assertFalse(solveCommand.isCommandComplete());
    }

    @Test
    void testNonNumericInputForIndex() {
        solveCommand = (SolveCommand) solveCommand.handleMultistepCommand("hello", questionBank);
        assertEquals("Invalid input. Please enter a number.", solveCommand.getCommandMessage());
        assertFalse(solveCommand.isCommandComplete());
    }

    @Test
    void testCorrectAnswer() {
        solveCommand = (SolveCommand) solveCommand.handleMultistepCommand("1", questionBank);
        solveCommand = (SolveCommand) solveCommand.handleMultistepCommand("Tokyo", questionBank);
        assertEquals("Correct!", solveCommand.getCommandMessage());
        assertTrue(solveCommand.isCommandComplete());
    }

    @Test
    void testIncorrectAnswer() {
        solveCommand = (SolveCommand) solveCommand.handleMultistepCommand("1", questionBank);
        solveCommand = (SolveCommand) solveCommand.handleMultistepCommand("Osaka", questionBank);
        assertEquals("Wrong answer, please try again.", solveCommand.getCommandMessage());
        assertFalse(solveCommand.isCommandComplete());
    }
}
