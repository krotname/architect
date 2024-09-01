package name.krot.spacebatleserver.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import name.krot.spacebatleserver.action.Refillable;

@Jacksonized
@SuperBuilder
public class RefillableSpaceShip extends SpaceShip implements Refillable {

    @Getter
    private int fuel;

    public void refuel(int deltaFuel) {
        this.fuel = this.fuel + deltaFuel;
    }

    public void burnFuel(int deltaFuel) {
        this.fuel = this.fuel - deltaFuel;
    }

    public boolean checkBurnFuel(int deltaFuel) {
        return this.fuel - deltaFuel >= 0;
    }
}
