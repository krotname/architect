package name.krot.spacebatleserver.core;

import name.krot.spacebatleserver.action.Command;
import name.krot.spacebatleserver.action.Movable;
import name.krot.spacebatleserver.action.MoveCommand;
import name.krot.spacebatleserver.action.commands.HardStopCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.mockito.Mockito.*;

class CommandExecutorWithMoveCommandTest {

    private Movable movableMock;
    private BlockingQueue<Command> commandQueue;
    private CommandExecutor executor;

    @BeforeEach
    void setUp() {
        // Создаем мок объекта Movable
        movableMock = mock(Movable.class);

        // Подготовка очереди команд и экземпляра CommandExecutor
        commandQueue = new LinkedBlockingQueue<>();
        executor = new CommandExecutor(commandQueue);

        // Регистрация MoveCommand в IoC контейнере
        ScopedIoCContainer iocContainer = ScopedIoCContainer.getIoCInstance();
        iocContainer.register("MoveCommand", args -> new MoveCommand((Movable) args[0], (Integer) args[1], (Integer) args[2]));
    }

    @Test
    void testMoveCommandExecutionInCommandExecutor() throws InterruptedException {
        // Инициализация начальной позиции
        when(movableMock.getPosition()).thenReturn(new Point(10, 20));

        // Добавляем MoveCommand в очередь
        MoveCommand moveCommand = (MoveCommand) MoveCommand.createCommand(movableMock, 5, -10);
        commandQueue.add(moveCommand);


        // Запускаем CommandExecutor в отдельном потоке
        Thread executorThread = new Thread(executor);
        executorThread.start();

        // Даём время на выполнение команды
        Thread.sleep(100);

        // Останавливаем CommandExecutor
        commandQueue.add(new HardStopCommand(executor));
        executorThread.join(); // Ожидаем завершения потока

        // Проверяем, что команда выполнилась и позиция была изменена
        Point expectedPoint = new Point(15, 10);
        verify(movableMock).setPosition(expectedPoint);
    }
}
