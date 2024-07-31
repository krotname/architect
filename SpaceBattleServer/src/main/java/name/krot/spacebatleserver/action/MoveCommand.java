package name.krot.spacebatleserver.action;

import lombok.RequiredArgsConstructor;

import java.awt.*;

@RequiredArgsConstructor
public class MoveCommand implements Command{

    private final Movable movable;
    private final Point newPoint;

    @Override
    public void execute() {
        movable.setPosition(newPoint);
    }
}
