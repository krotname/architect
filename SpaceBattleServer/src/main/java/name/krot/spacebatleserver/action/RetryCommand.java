package name.krot.spacebatleserver.action;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class RetryCommand implements Command {

    private final Command command;
    @Override
    public void execute() {
        command.execute();
    }
}
