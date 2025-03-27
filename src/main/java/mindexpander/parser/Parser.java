package mindexpander.parser;

// Commands
import mindexpander.commands.Command;
import mindexpander.commands.AddCommand;
import mindexpander.commands.HelpCommand;
import mindexpander.commands.ListCommand;
import mindexpander.commands.ExitCommand;
import mindexpander.commands.SolveCommand;
import mindexpander.commands.FindCommand;

// Exceptions
import mindexpander.commands.SolveCommandOneStep;
import mindexpander.exceptions.IllegalCommandException;

import mindexpander.data.QuestionBank;
import mindexpander.common.Messages;

/**
 * The {@code Parser} class is responsible for interpreting user commands
 * and returning the corresponding {@code CommandHandler} object.
 *
 * <p>This class processes user input, extracts the command, and determines
 * the appropriate action by creating and returning a relevant command object.</p>
 *
 * @author Wenyi
 * @version 1.0
 * @since 2025-03-06
 */
public class Parser {
    private Command ongoingCommand = null; // Tracks multistep command

    /**
     * Parses the user's input command and returns the appropriate {@code CommandHandler} object.
     *
     * <p>This method uses a switch expression to return the corresponding command object.</p>
     *
     * @param userEntry The full command input from the user.
     * @return The appropriate {@code CommandHandler} object based on the command.
     * @throws IllegalCommandException If the command is invalid or unrecognized.
     */
    public Command parseCommand (String userEntry, QuestionBank questionBank)
            throws IllegalCommandException {
        if (ongoingCommand != null && !ongoingCommand.isCommandComplete()) {
            // Continue processing the ongoing multistep command
            return ongoingCommand.handleMultistepCommand(userEntry, questionBank);
        }

        // Split into commands and details of task
        String[] stringParts = userEntry.split(" ", 2);
        String userCommand = stringParts[0];
        String taskDetails = stringParts.length > 1? stringParts[1] : "";

        // Handle commands
        return switch (userCommand.toLowerCase()) {
        case "help" -> new HelpCommand();
        case "exit" -> new ExitCommand();
        case "solve" -> {
            if (taskDetails.isEmpty()) {
                ongoingCommand = new SolveCommand();
                yield ongoingCommand;
            }
            yield new SolveCommandOneStep(taskDetails, questionBank);
        }
        case "add" -> new AddCommand();
        case "list" -> {
            if (taskDetails.trim().equalsIgnoreCase("answer")) {
                yield new ListCommand(questionBank, true);
            } else if (taskDetails.trim().isEmpty()) {
                yield new ListCommand(questionBank, false);
            } else {
                throw new IllegalCommandException(Messages.UNKNOWN_COMMAND_MESSAGE);
            }
        }
        case "find" -> {
            if (taskDetails.trim().isEmpty()) {
                throw new IllegalCommandException(Messages.UNKNOWN_COMMAND_MESSAGE);
            }

            String[] parts = taskDetails.trim().split("\\s+", 2);
            String questionType;
            String keyword;
            if (parts.length == 2
                    && (parts[0].equalsIgnoreCase("mcq")
                    || parts[0].equalsIgnoreCase("fitb"))) {
                questionType = parts[0].toLowerCase();
                keyword = parts[1];
            } else if (parts.length == 1) {
                if (parts[0].equalsIgnoreCase("mcq") || parts[0].equalsIgnoreCase("fitb")) {
                    throw new IllegalCommandException(Messages.UNKNOWN_COMMAND_MESSAGE);
                }
                questionType = "all";
                keyword = taskDetails;
            } else {
                throw new IllegalCommandException(Messages.UNKNOWN_COMMAND_MESSAGE);
            }
            yield new FindCommand(questionBank, questionType, keyword);
        }
        default -> throw new IllegalCommandException(Messages.UNKNOWN_COMMAND_MESSAGE);
        };
    }
}
