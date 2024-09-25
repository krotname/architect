package name.krot.spacebatleserver.core;

import name.krot.spacebatleserver.action.Command;
import name.krot.spacebatleserver.action.Injectable;
import name.krot.spacebatleserver.action.Movable;
import name.krot.spacebatleserver.action.UObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Disabled
class AdapterFactoryTest {

    private UObject mockUObject;
    private static IoCContainer mockIoC = ScopedIoCContainer.getIoCInstance();


    @BeforeEach
    public void setUp() {
        // Мокаем UObject для использования в тестах
        mockUObject = Mockito.mock(UObject.class);

        // Мокаем IoC контейнер для имитации вызовов
        mockIoC = mock(IoCContainer.class);

        // Подготавливаем резолвинг в IoC контейнере для методов интерфейса IMovable
        when(mockIoC.resolve("Spaceship.Operations.Movable:getposition.get", mockUObject))
                .thenReturn(new Point(1, 1));

        when(mockIoC.resolve("Spaceship.Operations.Movable:getvelocity.get", mockUObject))
                .thenReturn(new Point(0,  10));

        doNothing().when(mockIoC).resolve("Spaceship.Operations.IMovable:setposition.set", mockUObject, any(Vector.class));
    }

    @Test
    public void testGetPosition() {
        // Создаем адаптер для IMovable
        Movable adapter = AdapterFactory.createAdapter(Movable.class, mockUObject);

        // Вызываем метод getPosition и проверяем, что возвращается корректное значение
        Point position = adapter.getPosition();
        assertNotNull(position);
        assertEquals(1, position.getX());
        assertEquals(1, position.getY());

        // Проверяем, что IoC-контейнер был вызван с правильными параметрами
        verify(mockIoC).resolve("Spaceship.Operations.IMovable:getposition.get", mockUObject);
    }

    @Test
    public void testSetPosition() {
        // Создаем адаптер для IMovable
        Movable adapter = AdapterFactory.createAdapter(Movable.class, mockUObject);

        // Вызываем метод setPosition с новым значением Vector
        Point newPosition = new Point(5, 5);
        adapter.setPosition(newPosition);

        // Проверяем, что вызов метода IoC прошел с правильными аргументами
        verify(mockIoC).resolve("Spaceship.Operations.IMovable:setposition.set", mockUObject, newPosition);
    }

    @Test
    public void testGetVelocity() {
        // Создаем адаптер для IMovable
        Movable adapter = AdapterFactory.createAdapter(Movable.class, mockUObject);

        // Вызываем метод getVelocity и проверяем результат
        Point velocity = adapter.getVelocity();
        assertNotNull(velocity);
        assertEquals(0, velocity.getX());
        assertEquals(0, velocity.getY());

        // Проверяем вызов IoC-контейнера
        verify(mockIoC).resolve("Spaceship.Operations.IMovable:getvelocity.get", mockUObject);
    }

    @Test
    public void testUnsupportedMethod() {
        // Мокаем новый интерфейс без поддержки методов get/set
        Injectable mockDriveable = Mockito.mock(Injectable.class);

        // Проверяем, что UnsupportedOperationException выбрасывается для неизвестных методов
        Command adapter = AdapterFactory.createAdapter(Command.class, mockUObject);
        assertThrows(UnsupportedOperationException.class, adapter::execute);
    }
}
