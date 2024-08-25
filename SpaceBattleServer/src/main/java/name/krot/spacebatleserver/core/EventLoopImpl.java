package name.krot.spacebatleserver.core;

import name.krot.spacebatleserver.action.Command;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class EventLoopImpl implements EventLoop {

    ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
    PriorityBlockingQueue<Command> concurrentLinkedEventLoopQueue = new PriorityBlockingQueue<>();

    public void start() {

        Thread thread = new Thread(() -> {
            int work = 1;
            while (work > 0) {
                try {
                    Command take = concurrentLinkedEventLoopQueue.take();
                    executorService.execute(take::execute);
                } catch (InterruptedException e) {
                    work = 0;
                }
            }
        });
        thread.start();

    }

    public void putInLine(Command command) {
        concurrentLinkedEventLoopQueue.add(command);
    }

}
