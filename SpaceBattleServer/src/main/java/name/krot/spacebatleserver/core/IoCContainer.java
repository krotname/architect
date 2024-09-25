package name.krot.spacebatleserver.core;

import name.krot.spacebatleserver.action.Command;
import name.krot.spacebatleserver.action.Movable;
import name.krot.spacebatleserver.action.MoveCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class IoCContainer implements IoC {
    private final Map<String, Function<Object[], Object>> registry = new HashMap<>();

    @Override
    public <T> T resolve(String key, Object... args) {
        Function<Object[], Object> factory = registry.get(key);
        if (factory == null) {
            throw new IllegalArgumentException("Dependency not found: " + key);
        }
        return (T) factory.apply(args);
    }

    public void register(String key, Function<Object[], Object> factory) {
        registry.put(key, factory);
    }




}