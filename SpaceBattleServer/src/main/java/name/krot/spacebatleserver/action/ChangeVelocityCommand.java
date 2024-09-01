package name.krot.spacebatleserver.action;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import name.krot.spacebatleserver.model.MovableRotatableObject;

import java.awt.*;

@Builder
@RequiredArgsConstructor
public class ChangeVelocityCommand implements Command {

    private final MovableRotatableObject movableRotatableObject;
    private final Point point;

    @Override
    public void execute() {
        movableRotatableObject.setVelocity(point);
    }
}
