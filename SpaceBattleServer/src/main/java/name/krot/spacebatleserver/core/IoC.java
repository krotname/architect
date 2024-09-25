package name.krot.spacebatleserver.core;

public interface IoC {
    <T> T resolve(String key, Object... args);
}
