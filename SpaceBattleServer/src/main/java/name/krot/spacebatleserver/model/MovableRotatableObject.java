package name.krot.spacebatleserver.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import name.krot.spacebatleserver.action.Movable;
import name.krot.spacebatleserver.action.Rotatable;

import java.awt.*;
import java.util.UUID;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@SuperBuilder
public abstract class MovableRotatableObject implements Movable, Rotatable {

    @Getter
    @Setter
    UUID id;
    Point position;
    Point velocity;
    int angular;

    @Override
    public Point getVelocity() {
        return velocity;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point point) {
        position = point;
    }

    @Override
    public int getAngular() {
        return angular;
    }

    @Override
    public void setAngular(int angular) {
        this.angular = angular;
    }
}

