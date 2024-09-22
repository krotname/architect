package name.krot.spacebatleserver.core;

import name.krot.spacebatleserver.core.IoCContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class IoCContainerMultithreadingTest {
    private IoCContainer container;

    @BeforeEach
    void setUp() {
        container = ScopedIoCContainer.getIoCInstance();
        container.register("ThreadSafeService", args -> "ThreadSafeValue");
    }

    @Test
    void testResolveInMultipleThreads() throws InterruptedException {
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        // Для каждого потока проверяем разрешение зависимости
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    String result = container.resolve("ThreadSafeService");
                    assertEquals("ThreadSafeValue", result);
                } finally {
                    latch.countDown();
                }
            });
        }

        // Ожидаем завершения всех потоков
        assertTrue(latch.await(10, TimeUnit.SECONDS));
        executorService.shutdown();
    }
}
