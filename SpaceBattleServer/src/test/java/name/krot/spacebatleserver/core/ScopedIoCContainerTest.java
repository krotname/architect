package name.krot.spacebatleserver.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScopedIoCContainerTest {
    private ScopedIoCContainer container;

    @BeforeEach
    void setUp() {
        container = new ScopedIoCContainer();
    }

    @Test
    void testRegisterInScopeAndResolve() {
        container.newScope();
        container.registerInScope("ScopedService", args -> "ScopedValue");

        String result = container.resolve("ScopedService");
        assertEquals("ScopedValue", result);

        container.endScope();
    }

    @Test
    void testFallbackToGlobalScope() {
        // Регистрируем глобальную зависимость
        container.register("GlobalService", args -> "GlobalValue");

        // Входим в новую область
        container.newScope();

        // Разрешаем глобальную зависимость внутри области видимости
        String result = container.resolve("GlobalService");
        assertEquals("GlobalValue", result);

        // Завершаем область
        container.endScope();
    }

    @Test
    void testResolveInNewScopeNotFound() {
        container.newScope();

        // Ожидаем, что зависимость не будет найдена в области
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            container.resolve("UnknownService");
        });
        assertEquals("Dependency not found: UnknownService", exception.getMessage());

        container.endScope();
    }
}
