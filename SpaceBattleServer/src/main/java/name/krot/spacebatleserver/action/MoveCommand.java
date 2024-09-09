package name.krot.spacebatleserver.action;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import name.krot.spacebatleserver.core.IoC;

import java.awt.*;

@Builder
@RequiredArgsConstructor
public class MoveCommand implements Command {

    private final Movable movable;
    private final int deltaX;
    private final int deltaY;
    private static final IoC<Command> di = IoC.getCommandInstance();


    @Override
    public void execute() {
        int x = (int) Math.round(movable.getPosition().getX());
        int y = (int) Math.round(movable.getPosition().getY());
        Point point = new Point(x + deltaX, y + deltaY);
        movable.setPosition(point);
    }

    public static Command createCommand(Movable movable, int deltaX, int deltaY) {
        return di.resolve("MoveCommand", movable, deltaX, deltaY);
    }
}
