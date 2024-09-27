package name.krot.spacebatleserver.action.commands;

import name.krot.spacebatleserver.action.Command;
import name.krot.spacebatleserver.core.CommandExecutor;

public class StartCommand implements Command {
    private final CommandExecutor executor;

    public StartCommand(CommandExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        new Thread(executor).start();
    }
}