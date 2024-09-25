package name.krot.spacebatleserver.action;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import name.krot.spacebatleserver.core.ScopedIoCContainer;

import java.awt.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class MoveCommand implements Command {

    static {
        ScopedIoCContainer.getIoCInstance().register("MoveCommand", args -> new MoveCommand((Movable) args[0], (Integer) args[1], (Integer) args[2]));
    }

    private Movable movable;
    private int deltaX;
    private int deltaY;

    @Override
    public void execute() {
        int x = (int) Math.round(movable.getPosition().getX());
        int y = (int) Math.round(movable.getPosition().getY());
        Point point = new Point(x + deltaX, y + deltaY);
        movable.setPosition(point);
    }

    public static Command createCommand(Movable movable, int deltaX, int deltaY) {

        return ScopedIoCContainer.getIoCInstance().resolve("MoveCommand", movable, deltaX, deltaY);
    }
}
