package mindexpander.tests;

import mindexpander.commands.CommandResult;
import mindexpander.commands.ListCommand;
import mindexpander.common.Messages;
import mindexpander.exceptions.IllegalCommandException;
import mindexpander.parser.Parser;
import mindexpander.data.QuestionBank;
import mindexpander.data.question.FillInTheBlanks;
import mindexpander.storage.StorageFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListCommandTest extends DefaultTest {
    private QuestionBank questionBank;
    private ListCommand listCommand;
    private CommandResult commandResult;
    private StorageFile storage;

    @BeforeEach
    void setup() {
        questionBank = new QuestionBank();
        questionBank.addQuestion(new FillInTheBlanks("1 + 1 = __", "2"));
        questionBank.addQuestion(new FillInTheBlanks("__ MRT Station is the closest station to NUS", "Kent Ridge"));
    }

    @Test
    public void testListCommandWithNoQuestions() {
        questionBank = new QuestionBank();
        listCommand = new ListCommand(questionBank, false);
        commandResult = listCommand.execute();
        assertEquals("You have no questions yet!", commandResult.commandResultToUser);
    }

    @Test
    public void testListWithAnswerCommandWithNoQuestions() {
        questionBank = new QuestionBank();
        listCommand = new ListCommand(questionBank, true);
        commandResult = listCommand.execute();
        assertEquals("You have no questions yet!", commandResult.commandResultToUser);
    }

    @Test
    public void testListCommandWithQuestions() {
        listCommand = new ListCommand(questionBank, false);
        commandResult = listCommand.execute();
        assertEquals("Here are the questions you have stored:", commandResult.commandResultToUser);
        ArrayList<String> questionBankStringArray = new ArrayList<>();
        for (int i = 0; i < questionBank.getQuestionCount(); i++){
            String question = commandResult.showAnswer
                    ? questionBank.getQuestion(i).toString()
                    : questionBank.getQuestion(i).toStringNoAnswer();
            questionBankStringArray.add((i + 1) + Messages.DISPLAYED_INDEX_OFFSET + question);
        }
        assertEquals(List.of(
                "1. FITB: 1 + 1 = __",
                "2. FITB: __ MRT Station is the closest station to NUS"
        ), questionBankStringArray);
    }

    @Test
    public void testListWithAnswerCommandWithQuestions() {
        listCommand = new ListCommand(questionBank, true);
        commandResult = listCommand.execute();
        assertEquals("Here are the questions you have stored:", commandResult.commandResultToUser);
        ArrayList<String> questionBankStringArray = new ArrayList<>();
        for (int i = 0; i < questionBank.getQuestionCount(); i++){
            String question = commandResult.showAnswer
                    ? questionBank.getQuestion(i).toString()
                    : questionBank.getQuestion(i).toStringNoAnswer();
            questionBankStringArray.add((i + 1) + Messages.DISPLAYED_INDEX_OFFSET + question);
        }
        assertEquals(List.of(
                "1. FITB: 1 + 1 = __ [Answer: 2]",
                "2. FITB: __ MRT Station is the closest station to NUS [Answer: Kent Ridge]"
        ), questionBankStringArray);
    }

    @Test
    public void testListCommandWithWrongArgument() {
        String userInput = "list banana";
        IllegalCommandException thrown = assertThrows(IllegalCommandException.class
                , () -> new Parser().parseCommand(userInput, questionBank));
        assertEquals("You have entered an unknown command." +
                " Please refer to the user guide, or type <help> to display the available commands"
                , thrown.getMessage());
    }
}
