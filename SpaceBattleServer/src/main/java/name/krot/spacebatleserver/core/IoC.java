package name.krot.spacebatleserver.core;

import name.krot.spacebatleserver.action.Command;

public interface IoC<T> {

    class SingletonHolder {
        private SingletonHolder() {
        }
        public static final IoCImpl HOLDER_INSTANCE = new IoCImpl<Command>();
    }

    static IoC<Command> getCommandInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    T resolve(String key, Object... params);

    void register(String key, Object... params);
}
