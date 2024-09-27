package name.krot.spacebatleserver.action.commands;

import name.krot.spacebatleserver.action.Command;
import name.krot.spacebatleserver.core.CommandExecutor;

public class HardStopCommand implements Command {
    private final CommandExecutor executor;

    public HardStopCommand(CommandExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        executor.stop();
    }
}