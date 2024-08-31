package name.krot.spacebatleserver.core;

import name.krot.spacebatleserver.action.Command;

public interface EventLoop {

    static EventLoop getInstance() {
        return new EventLoopImpl();
    }

    void start();

    void putInLine(Command command);

}
