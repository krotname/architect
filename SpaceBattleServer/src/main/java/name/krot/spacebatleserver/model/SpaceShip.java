package name.krot.spacebatleserver.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import name.krot.spacebatleserver.model.action.Movable;
import name.krot.spacebatleserver.model.action.Rotatable;

import java.util.UUID;

@Jacksonized
@Builder
@Value
public class SpaceShip implements Movable, Rotatable {
    UUID id;

    @Override
    public void move() {

    }

    @Override
    public void rotate() {

    }
}
