package name.krot.spacebatleserver.action;

import name.krot.spacebatleserver.core.IoCContainer;
import name.krot.spacebatleserver.core.ScopedIoCContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MoveCommandTest {
    private Movable movable;
    private IoCContainer container;

    @BeforeEach
    void setUp() {
        // Мокируем объект Movable
        movable = Mockito.mock(Movable.class);

        // Устанавливаем начальную позицию
        when(movable.getPosition()).thenReturn(new Point(10, 10));

        // Инициализируем контейнер
        container = ScopedIoCContainer.getIoCInstance();

        // Регистрируем MoveCommand в контейнере
        container.register("MoveCommand", args -> new MoveCommand((Movable) args[0], (int) args[1], (int) args[2]));
    }

    @Test
    void testMoveCommandExecution() {
        // Создаем команду
        MoveCommand command = (MoveCommand) MoveCommand.createCommand(movable, 5, -3);

        // Выполняем команду
        command.execute();

        // Проверяем, что позиция была изменена
        verify(movable).setPosition(new Point(15, 7));
    }

    @Test
    void testCreateCommandWithIoC() {
        // Создаем команду через IoC контейнер
        MoveCommand command = (MoveCommand) MoveCommand.createCommand(movable, 3, 4);

        assertNotNull(command);
        assertEquals(3, command.getDeltaX());
        assertEquals(4, command.getDeltaY());
    }
}
