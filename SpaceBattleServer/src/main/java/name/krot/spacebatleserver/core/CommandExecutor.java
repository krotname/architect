package name.krot.spacebatleserver.core;

import name.krot.spacebatleserver.action.Command;

import java.util.concurrent.BlockingQueue;

public class CommandExecutor implements Runnable {
    private final BlockingQueue<Command> commandQueue;
    private volatile boolean running = true;

    public CommandExecutor(BlockingQueue<Command> commandQueue) {
        this.commandQueue = commandQueue;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Command command = commandQueue.take(); // Ожидание команды
                try {
                    command.execute();
                } catch (Exception e) {
                    System.err.println("Error executing command: " + e.getMessage());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }
}
