package name.krot.spacebatleserver.model.action;

import java.awt.*;

public interface Movable {
    Point getVelocity();
    Point getPosition();
    void setPosition(Point point);
}
