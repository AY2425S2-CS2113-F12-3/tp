package mindexpander.ui;

import mindexpander.common.Messages;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class TextUi {
    // Attributes
    private final Scanner in;
    private final PrintStream out;

    // Constructor
    public TextUi() {
        System.out.println(Messages.LINE);
        System.out.println(Messages.MINDEXPANDERLOGO);
        System.out.println(Messages.TEAM_NAME);
        System.out.println(Messages.WELCOME_MESSAGE);
        System.out.println(Messages.LINE);
        this.in = new Scanner(System.in);
        this.out = new PrintStream(System.out);
    }

    // Methods
    public void printToUser(String... message) {
        System.out.println(Messages.LINE);
        for (String m : message) {
            System.out.println(m);
        }
        System.out.println(Messages.LINE);
    }

    public void printInitFailedMessage() {
        printToUser(Messages.FAILED_MESSAGE);
    }

    public void printGoodbyeMessage() {
        System.out.println(Messages.GOODBYE_MESSAGE);
    }

    // Returns true if the user input is invalid
    public boolean invalidInput(String input) {
        return input.trim().isEmpty();
    }

    public void enterMainMenu() {
        in.nextLine();
        clearUserScreen();
    }

    public String getUserCommand() {
        printToUser(Messages.MENU_MESSAGE);
        String input = in.nextLine();

        while (invalidInput(input)) {
            input = in.nextLine();
        }

        printToUser("[Command you entered: " + input + "]");
        return input.trim();
    }

    public void clearUserScreen() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.println("\033\143");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void displayResults(String message) {
        printToUser(message);
    }
}
