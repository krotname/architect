package name.krot.spacebatleserver.action;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.awt.*;

@Builder
@RequiredArgsConstructor
public class MoveCommand implements Command {

    private final Movable movable;
    private final int deltaX;
    private final int deltaY;

    @Override
    public void execute() {
        int x = (int) Math.round(movable.getPosition().getX());
        int y = (int) Math.round(movable.getPosition().getY());
        Point point = new Point(x + deltaX, y + deltaY);
        movable.setPosition(point);
    }

    public static MoveCommand createCommand(Movable movable, int deltaX, int deltaY) {
        return new MoveCommand(movable, deltaX, deltaY);
    }
}
