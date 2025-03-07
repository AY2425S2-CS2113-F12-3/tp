package mindexpander.commands;

/**
 * The {@code HelpCommand} class updates display message
 * to show the list of commands and their functions.
 *
 * @author Wenyi
 * @version v0.2
 * @since 2025-03-06
 */
public class HelpCommand extends Command {
    /*
     * NOTE for future devs: update the HELP_MESSAGE string with
     * whatever new features are implemented.
     */
    private final String HELP_MESSAGE = """
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
            """;

    public HelpCommand() {
        updateCommandMessage(HELP_MESSAGE);
    }
}
