package mindexpander.commands;

import mindexpander.data.QuestionBank;

import mindexpander.exceptions.IllegalCommandException;

/**
 * The {@code Command} class represents a generic command that can be executed.
 * It provides a base structure for commands, including a message and the ability
 * to determine whether the program should continue running after execution.
 */
public class Command {
    protected String commandMessage;
    protected boolean isComplete = true; // To signal completion of multistep commands.

    /**
     * Updates the command message associated with this command.
     *
     * @param commandMessage the new message to be associated with this command.
     */
    protected void updateCommandMessage(String commandMessage) {
        this.commandMessage = commandMessage;
    }

    /**
     * Returns the command message associated with this command. Useful for testing commands.
     *
     * @return {@code commandMessage} the command message associated with this command.
     */
    public String getCommandMessage() {
        return commandMessage;
    }

    /**
     * Determines whether the program should continue running after executing this command.
     * Returns {@code true} by default. Is overwritten by {@code ExitCommand} to return false.
     *
     * @return {@code true} if the program should continue running, {@code false} otherwise.
     */
    public boolean keepProgramRunning() {
        return true;
    }

    /**
     * Executes the command and returns the result as a string.
     * This method can throw an {@code IllegalCommandException} if the command execution
     * encounters an error or violates any rules.
     *
     * @return the result of the command execution as a string.
     * @throws IllegalCommandException if the command execution is illegal or invalid.
     */
    public CommandResult execute() throws IllegalCommandException {
        return new CommandResult(commandMessage);
    }

    // To handle commands with multiple steps

    /**
     * Checks if a command is done running by returning the flag used to
     * check the completion status of a command. {@code isComplete} will
     * be returned as true by default.
     *
     * @return the status of the command's completion.
     */
    public boolean isCommandComplete() {
        return isComplete;
    }

    /**
     * Executes commands with multiple steps that require new user input for
     * each step. Used by child commands via overwriting this with their respective
     * switch statement logic.
     *
     * @return an instance of the command.
     */
    public Command handleMultistepCommand(String userCommand, QuestionBank questionBank) {
        // NOTE: if this is thrown during runtime, a single step command was mistakenly used
        // as a multistep command in parser.
        throw new UnsupportedOperationException("Command is not a multistep command.");
    }
}
