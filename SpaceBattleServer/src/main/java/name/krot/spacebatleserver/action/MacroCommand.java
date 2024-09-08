package name.krot.spacebatleserver.action;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class MacroCommand implements Command{
    private final CheckFuelCommand checkFuelCommand;
    private final MoveCommand moveCommand;
    private final BurnFuelCommand burnFuelCommand;

    @Override
    public void execute() { // todo test
        checkFuelCommand.execute();
        moveCommand.execute();
        burnFuelCommand.execute();
    }
}
