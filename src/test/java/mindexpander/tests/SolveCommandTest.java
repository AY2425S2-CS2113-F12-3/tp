package mindexpander.tests;

import mindexpander.commands.Command;
import mindexpander.commands.SolveCommand;
import mindexpander.data.QuestionBank;
import mindexpander.data.question.FillInTheBlanks;
import mindexpander.data.question.MultipleChoice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolveCommandTest {
    private QuestionBank questionBank;

    @BeforeEach
    public void setUp() {
        questionBank = new QuestionBank();
        // Add test questions - note answers are now letter options
        List<String> options = Arrays.asList("4", "3", "2", "5");
        questionBank.addQuestion(new MultipleChoice("What is 2+2?", "4", options));
        questionBank.addQuestion(new FillInTheBlanks("The capital of France is ____.", "Paris"));
    }

    @Test
    public void testGetQuestionIndex_validIndex() {
        SolveCommand command = new SolveCommand("1", questionBank);
        String message = command.getCommandMessage();
        assertTrue(message.startsWith("Attempting question 1:"));
        assertTrue(message.contains("MCQ: What is 2+2?"));
        assertTrue(message.contains("A. ") || message.contains("B. ") || message.contains("C. "));
    }

    @Test
    public void testHandleMultistepCommand_correctMCQAnswer() {
        SolveCommand command = new SolveCommand("1", questionBank);
        // Get the displayed options to find the correct letter
        String message = command.getCommandMessage();
        String correctOption = getCorrectOptionFromMessage(message, "4");

        Command result = command.handleMultistepCommand(correctOption, questionBank);
        assertEquals("Correct!", result.getCommandMessage());
    }

    @Test
    public void testHandleMultistepCommand_wrongMCQAnswer() {
        SolveCommand command = new SolveCommand("1", questionBank);
        // Get the displayed options to find a wrong letter
        String message = command.getCommandMessage();
        String wrongOption = getWrongOptionFromMessage(message, "4");

        Command result = command.handleMultistepCommand(wrongOption, questionBank);
        assertEquals("Wrong answer, would you like to try again? [Y/N]", result.getCommandMessage());
    }

    @Test
    public void testHandleMultistepCommand_correctFillInTheBlanks() {
        SolveCommand command = new SolveCommand("2", questionBank);
        Command result = command.handleMultistepCommand("Paris", questionBank);
        assertEquals("Correct!", result.getCommandMessage());
    }

    @Test
    public void testHandleMultistepCommand_wrongFillInTheBlanks() {
        SolveCommand command = new SolveCommand("2", questionBank);
        Command result = command.handleMultistepCommand("London", questionBank);
        assertEquals("Wrong answer, would you like to try again? [Y/N]", result.getCommandMessage());
    }

    // Helper methods to deal with shuffled options
    private String getCorrectOptionFromMessage(String message, String correctAnswer) {
        String[] lines = message.split("\n");
        for (String line : lines) {
            if (line.contains(correctAnswer)) {
                return line.trim().substring(0, 1); // Get the letter prefix
            }
        }
        return "";
    }

    private String getWrongOptionFromMessage(String message, String correctAnswer) {
        String[] lines = message.split("\n");
        for (String line : lines) {
            if (line.length() > 2 && line.contains(".") && !line.contains(correctAnswer)) {
                return line.trim().substring(0, 1); // Get the letter prefix
            }
        }
        return "";
    }

    @Test
    public void testGetQuestionIndex_invalidIndex() {
        SolveCommand command = new SolveCommand("0", questionBank);
        assertEquals("Invalid question number. Please enter a valid index.", command.getCommandMessage());

        command = new SolveCommand("3", questionBank);
        assertEquals("Invalid question number. Please enter a valid index.", command.getCommandMessage());
    }

    @Test
    public void testGetQuestionIndex_nonNumeric() {
        SolveCommand command = new SolveCommand("abc", questionBank);
        assertEquals("Invalid question number. Please enter a valid index.", command.getCommandMessage());
    }

    @Test
    public void testEmptyQuestionBank() {
        QuestionBank emptyBank = new QuestionBank();
        SolveCommand command = new SolveCommand("1", emptyBank);
        assertEquals("Question bank is empty. Please add a question first.", command.getCommandMessage());
    }
}
