package mindexpander;

import mindexpander.parser.Parser;

import mindexpander.data.QuestionBank;
import mindexpander.exceptions.IllegalCommandException;
import mindexpander.ui.TextUi;

import mindexpander.commands.*;

import mindexpander.common.Messages;

public class Main {
    // Attributes
    private TextUi ui;
    private static QuestionBank questionBank;

    // Constructor
    public static void main(String[] args) {
        new Main().run();
        questionBank = new QuestionBank();
    }

    // Methods
    // Run MindExpander
    public void run() {
        start();
        runUserCommandUntilTermination();
    }

    // Start function: Instantiates the TextUI class
    private void start() {
        try {
            this.ui = new TextUi();
            // Initialise storage and data here as well
            ui.enterMainMenu();
        } catch (Exception e) {
            ui.printInitFailedMessage();
        }
    }

    // Runs the program until user enters 'exit'
    private void runUserCommandUntilTermination() {
        // New command variable
        Command command;
        // Temporary state for exit, before adding in command class
        boolean exit = false;
        do {
            String userCommand = ui.getUserCommand();

            command = new Parser().parseCommand(userCommand);
            try {
                String commandResult = command.execute();
                ui.displayResults(commandResult);
            } catch (IllegalCommandException e) {
                ui.printToUser(Messages.UNKNOWN_COMMAND_MESSAGE);
            }

        } while (!ExitCommand.isExit(command));
    }
}