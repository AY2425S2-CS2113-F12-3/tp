package mindexpander;

import mindexpander.commands.CommandResult;
import mindexpander.parser.Parser;

import mindexpander.data.QuestionBank;
import mindexpander.exceptions.IllegalCommandException;
import mindexpander.storage.StorageFile;
import mindexpander.ui.TextUi;

import mindexpander.commands.Command;

/**
 * Entry point of Mindexpander application.
 * Initialises the application and starts the interaction with the user.
 */
public class Main {
    // Attributes
    private QuestionBank questionBank;
    private QuestionBank lastShownQuestionBank;
    private StorageFile storage;
    private TextUi ui;

    // Constructor
    public static void main(String[] args) {
        new Main().run();
    }

    // Methods
    // Run MindExpander
    public void run() {
        start();
        runUserCommandUntilTermination(questionBank);
    }

    // Start function: Instantiates the TextUI class
    private void start() {
        try {
            // Initialise main business components
            this.ui = new TextUi();
            this.storage = new StorageFile();
            this.questionBank = storage.load();
            this.lastShownQuestionBank = questionBank;
        } catch (Exception e) {
            ui.printInitFailedMessage();
        }
    }

    // Runs the program until user enters 'exit'
    private void runUserCommandUntilTermination(QuestionBank questionBank) {
        // New command variable
        Command command;
        // Temporary state for exit, before adding in command class
        boolean isRunning = true;

        do {
            String userCommand = ui.getUserCommand();

            try {
                command = new Parser().parseCommand(userCommand, questionBank, lastShownQuestionBank);
                CommandResult commandResult = command.execute();
                recordResult(commandResult);
                storage.save(questionBank);
                ui.displayResults(commandResult);

                while (!command.isCommandComplete()) {
                    String input = ui.nextLine();
                    manageMultistepCommand(input, command, questionBank, lastShownQuestionBank);
                    storage.save(questionBank);
                    ui.displayResults(command.execute());
                }

                isRunning = command.keepProgramRunning();
            } catch (IllegalCommandException e) {
                ui.printToUser(e.getMessage());
            }

        } while (isRunning);
    }

    // Records the last shown list
    private void recordResult(CommandResult commandResult) {
        final QuestionBank questionBank = commandResult.getQuestionBank();
        if (!questionBank.isEmpty()) {
            lastShownQuestionBank = questionBank;
        }
    }

    private void manageMultistepCommand(String input, Command command, QuestionBank questionBank,
        QuestionBank lastShownQuestionBank) {
        if (command.isUsingLastShownQuestionBank()) {
            command.handleMultistepCommand(input, lastShownQuestionBank);
        } else {
            command.handleMultistepCommand(input, questionBank);
        }
    }
}
