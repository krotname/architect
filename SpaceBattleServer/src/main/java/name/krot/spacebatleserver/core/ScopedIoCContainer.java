package name.krot.spacebatleserver.core;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ScopedIoCContainer extends IoCContainer {
    // Обеспечивает создание отдельного реестра для каждого потока
    private final ThreadLocal<Map<String, Function<Object[], Object>>> scopedRegistry = ThreadLocal.withInitial(HashMap::new);

    // Начало новой области видимости для текущего потока
    public void newScope() {
        // Создаем новую область видимости, привязанную к текущему потоку
        scopedRegistry.set(new HashMap<>());
    }

    // Завершение области видимости
    public void endScope() {
        // Удаляем локальную область, освобождая память
        scopedRegistry.remove();
    }

    // Переопределение метода resolve для учета области видимости
    @Override
    public <T> T resolve(String key, Object... args) {
        Map<String, Function<Object[], Object>> currentScope = scopedRegistry.get();

        // Проверяем наличие зависимости в области текущего потока
        if (currentScope.containsKey(key)) {
            return (T) currentScope.get(key).apply(args);
        }

        // Если нет локальной зависимости, обращаемся к глобальному контейнеру
        return super.resolve(key, args);
    }

    // Регистрация зависимости в области видимости текущего потока
    public void registerInScope(String key, Function<Object[], Object> factory) {
        scopedRegistry.get().put(key, factory);
    }

    static class SingletonHolder {
        private SingletonHolder() {
        }
        public static final IoCContainer HOLDER_INSTANCE = new ScopedIoCContainer();
    }

    public static IoCContainer getIoCInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }
}