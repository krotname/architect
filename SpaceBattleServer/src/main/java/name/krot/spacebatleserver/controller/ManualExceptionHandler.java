package name.krot.spacebatleserver.controller;

import name.krot.spacebatleserver.action.Command;
import name.krot.spacebatleserver.action.LogExceptionCommand;
import name.krot.spacebatleserver.core.EventLoop;

public class ManualExceptionHandler {

    static ManualExceptionHandler getInstance() {
        return new ManualExceptionHandler();
    }

    EventLoop eventLoop = EventLoop.getInstance();

    public void handleDoubleRetry(Command command, Exception exception) {
        try {
            eventLoop.putInLine(command);
        } catch (Exception e) {
            handleRetry(command, exception);
        }
    }
    public void handleRetry(Command command, Exception exception) {
        try {
            eventLoop.putInLine(command);
        } catch (Exception e) {
            handleLog(exception);
        }
    }

    public void handleLog(Exception exception) { //5
        eventLoop.putInLine(() -> LogExceptionCommand.builder().exception(exception).build());
    }

}
