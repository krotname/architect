package name.krot.spacebatleserver.action;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.awt.*;
@Builder
@RequiredArgsConstructor
public class MoveCommand implements Command{

    private final Movable movable;
    private final Point newPoint;

    @Override
    public void execute() {
        movable.setPosition(newPoint);
    }

    public static MoveCommand createCommand(Movable movable, Point newPoint) {
        return new MoveCommand(movable, newPoint);
    }
}
