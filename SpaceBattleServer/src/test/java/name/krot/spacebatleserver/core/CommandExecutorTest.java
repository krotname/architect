package name.krot.spacebatleserver.core;

import name.krot.spacebatleserver.action.Command;
import name.krot.spacebatleserver.action.commands.HardStopCommand;
import name.krot.spacebatleserver.action.commands.StartCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandExecutorTest {
    private BlockingQueue<Command> commandQueue;
    private CommandExecutor executor;

    @BeforeEach
    void setUp() {
        commandQueue = new LinkedBlockingQueue<>();
        executor = new CommandExecutor(commandQueue);
    }

    @Test
    void testStartCommand() throws InterruptedException {
        Command startCommand = new StartCommand(executor);
        startCommand.execute();
        Thread.sleep(100); // Даем время на запуск
        assertTrue(executor.isRunning()); // Проверяем, что поток работает
    }

    @Test
    void testHardStopCommand() throws InterruptedException {
        Command startCommand = new StartCommand(executor);
        startCommand.execute();
        Thread.sleep(100);
        Command stopCommand = new HardStopCommand(executor);
        stopCommand.execute();
        assertFalse(executor.isRunning()); // Проверяем, что поток остановился
    }

    // Дополнительные тесты для SoftStopCommand
}
