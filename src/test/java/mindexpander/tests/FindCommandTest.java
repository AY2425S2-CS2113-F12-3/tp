package mindexpander.tests;

import mindexpander.commands.CommandResult;
import mindexpander.commands.FindCommand;
import mindexpander.common.Messages;
import mindexpander.data.QuestionBank;
import mindexpander.data.question.FillInTheBlanks;
import mindexpander.exceptions.IllegalCommandException;
import mindexpander.parser.Parser;
import mindexpander.storage.StorageFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindCommandTest extends DefaultTest {
    private QuestionBank questionBank;
    private FindCommand findCommand;
    private CommandResult commandResult;
    private StorageFile storage;

    @BeforeEach
    void setup() {
        questionBank = new QuestionBank();
        questionBank.addQuestion(new FillInTheBlanks("1 + 1 = __", "2"));
        questionBank.addQuestion(new FillInTheBlanks("__ MRT Station is the closest station to NUS", "Kent Ridge"));
    }

    @Test
    void testFindCommandWithNoTypeAndNoKeywordAndNoQuestions() {
        questionBank = new QuestionBank();
        String userInput = "find";
        IllegalCommandException thrown = assertThrows(IllegalCommandException.class
                , () -> new Parser().parseCommand(userInput, questionBank));
        assertEquals("You have entered an unknown command." +
                        " Please refer to the user guide, or type <help> to display the available commands"
                , thrown.getMessage());
    }

    @Test
    void testFindCommandWithNoTypeAndKeywordAndNoQuestions() {
        questionBank = new QuestionBank();
        findCommand = new FindCommand(questionBank, "all", "MRT");
        commandResult = findCommand.execute();
        assertEquals("No questions with MRT found!", commandResult.commandResultToUser);
    }

    @Test
    void testFindCommandWithNoTypeAndKeywordAndQuestions() {
        findCommand = new FindCommand(questionBank, "all", "MRT");
        commandResult = findCommand.execute();
        assertEquals("Here are the questions with MRT:", commandResult.commandResultToUser);
        ArrayList<String> filteredQuestionBankStringArray = new ArrayList<>();
        for (int i = 0; i < commandResult.questionBank.getQuestionCount(); i++){
            String question = commandResult.questionBank.getQuestion(i).toStringNoAnswer();
            filteredQuestionBankStringArray.add((i + 1) + Messages.DISPLAYED_INDEX_OFFSET + question);
        }
        assertEquals(List.of("1. FITB: __ MRT Station is the closest station to NUS"), filteredQuestionBankStringArray);
    }

    @Test
    void testFindCommandWithTypeAndNoKeywordAndNoQuestions() {
        questionBank = new QuestionBank();
        String userInput = "find fitb";
        IllegalCommandException thrown = assertThrows(IllegalCommandException.class
                , () -> new Parser().parseCommand(userInput, questionBank));
        assertEquals("You have entered an unknown command." +
                        " Please refer to the user guide, or type <help> to display the available commands"
                , thrown.getMessage());
    }

    @Test
    void testFindCommandWithTypeAndKeywordAndNoQuestions() {
        questionBank = new QuestionBank();
        findCommand = new FindCommand(questionBank, "fitb", "MRT");
        commandResult = findCommand.execute();
        assertEquals("No questions with MRT found!", commandResult.commandResultToUser);
    }

    @Test
    void testFindCommandWithTypeAndKeywordAndQuestions() {
        findCommand = new FindCommand(questionBank, "fitb", "MRT");
        commandResult = findCommand.execute();
        assertEquals("Here are the questions with MRT:", commandResult.commandResultToUser);
        ArrayList<String> filteredQuestionBankStringArray = new ArrayList<>();
        for (int i = 0; i < commandResult.questionBank.getQuestionCount(); i++){
            String question = commandResult.questionBank.getQuestion(i).toStringNoAnswer();
            filteredQuestionBankStringArray.add((i + 1) + Messages.DISPLAYED_INDEX_OFFSET + question);
        }
        assertEquals(List.of("1. FITB: __ MRT Station is the closest station to NUS"), filteredQuestionBankStringArray);
    }
}
