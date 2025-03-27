package mindexpander.tests;

import mindexpander.commands.HelpCommand;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit test for {@code HelpCommand} class.
 *
 * @author Wenyi
 */
public class HelpCommandTest {

    @Test
    public void testHelpCommandMessage() {
        HelpCommand helpCommand = new HelpCommand();

        String expectedMessage = """
            Welcome to MindExpander!
            
            List of commands:
            1. help
                - Print a list of commands.
            2. add
                - Add a question to the list.
            3. list
                - List all questions added.
            4. solve
                - Attempt to solve a question.
            5. edit
                - Edit a question in the list.
            6. find
                - Find a question in the list that contains a specific keyword.
            """;

        assertEquals(expectedMessage, helpCommand.getCommandMessage(), "Help message does not match expected output.");
    }

}
