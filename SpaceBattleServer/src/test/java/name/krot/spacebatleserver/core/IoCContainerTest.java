package name.krot.spacebatleserver.core;

import name.krot.spacebatleserver.action.MoveCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IoCContainerTest {

    private IoCContainer container;

    @BeforeEach
    void setUp() {
        container = ScopedIoCContainer.getIoCInstance();
    }

    @Test
    void testRegisterAndResolve2() {
        // Регистрируем объект
        container.register("TestService", args -> "TestValue");

        // Проверяем разрешение зависимости
        String result = container.resolve("TestService");
        assertEquals("TestValue", result);
    }

    @Test
    void testResolveDependencyNotFound() {
        // Попытка разрешить несуществующую зависимость должна выкидывать исключение
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            container.resolve("UnknownService");
        });
        assertEquals("Dependency not found: UnknownService", exception.getMessage());
    }

    @Test
    void testRegisterAndResolve() {
        container.register("MoveCommand", args -> new MoveCommand());
        MoveCommand moveCommand = container.resolve("MoveCommand");
        assertNotNull(moveCommand);
    }


    @Test
    void testMultithreadingScopes() throws InterruptedException {
        ScopedIoCContainer container = new ScopedIoCContainer();

        Runnable task = () -> {
            container.newScope();
            container.registerInScope("threadName", args -> Thread.currentThread().getName());
            String resolvedName = container.resolve("threadName");
            System.out.println("Thread: " + resolvedName);
            container.endScope();
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

}
