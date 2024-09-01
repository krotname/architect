package name.krot.spacebatleserver.action;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import name.krot.spacebatleserver.exceptions.CheckFuelException;

@Builder
@RequiredArgsConstructor
public class CheckFuelCommand implements Command {
    private final Refillable refillableObject;
    private final int deltaFuel;

    @Override
    public void execute() {
        if (!refillableObject.checkBurnFuel(deltaFuel)) {
            throw new CheckFuelException("Недостаточно топлива");
        }
    }
}
