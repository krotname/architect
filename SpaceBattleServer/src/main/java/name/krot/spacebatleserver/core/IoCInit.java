package name.krot.spacebatleserver.core;

import name.krot.spacebatleserver.action.MoveCommand;

public class IoCInit {

    public static IoC startEmpty() {
        return new IoCImpl();
    }

    public static IoC init() {
        IoC IoC = startEmpty();
        return IoC;
    }

}
