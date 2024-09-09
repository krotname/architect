package name.krot.spacebatleserver.core;

import name.krot.spacebatleserver.action.Movable;
import name.krot.spacebatleserver.action.MoveCommand;

import java.util.concurrent.ConcurrentHashMap;

public class IoCImpl<T> implements IoC<T> {
    ConcurrentHashMap<String, Object[]> IoC = new ConcurrentHashMap<>();

    @Override
    public T resolve(String key, Object... params) {
        if ("MoveCommand".equals(key)) {
            return (T) new MoveCommand((Movable) params[0], (int) params[1], (int) params[2]);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void register(String key, Object... params) {
        Object[] put = IoC.put(key, params);
    }
}