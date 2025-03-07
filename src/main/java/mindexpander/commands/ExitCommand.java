package mindexpander.commands;

import mindexpander.common.Messages;

public class ExitCommand extends Command{
    public ExitCommand() {
        updateCommandMessage(Messages.GOODBYE_MESSAGE);
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
