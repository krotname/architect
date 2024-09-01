package name.krot.spacebatleserver.action;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class BurnFuelCommand implements Command {

    private final Refillable refillableObject;
    private final int deltaFuel;

    @Override
    public void execute() {
        refillableObject.burnFuel(deltaFuel);
    }
}
