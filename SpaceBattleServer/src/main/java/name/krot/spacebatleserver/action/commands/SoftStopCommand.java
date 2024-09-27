package name.krot.spacebatleserver.action.commands;

import name.krot.spacebatleserver.action.Command;
import name.krot.spacebatleserver.core.CommandExecutor;

public class SoftStopCommand implements Command {
    private final CommandExecutor executor;

    public SoftStopCommand(CommandExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        executor.stop();
    }
}