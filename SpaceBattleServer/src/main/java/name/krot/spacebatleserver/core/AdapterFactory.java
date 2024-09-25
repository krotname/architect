package name.krot.spacebatleserver.core;

import name.krot.spacebatleserver.action.UObject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AdapterFactory {

    private static IoCContainer container = ScopedIoCContainer.getIoCInstance();

    // Общий метод для создания адаптера для любого интерфейса
    @SuppressWarnings("unchecked")
    public static <T> T createAdapter(Class<T> interfaceType, UObject obj) {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();

                // Определяем, является ли метод getter'ом или setter'ом
                if (methodName.startsWith("get")) {
                    return container.resolve(interfaceType.getName() + ":" + methodName.toLowerCase() + ".get", obj);
                } else if (methodName.startsWith("set")) {
                    container.resolve(interfaceType.getName() + ":" + methodName.toLowerCase() + ".set", obj, args[0]);
                    return null;
                }

                // Если метод не поддерживается, выбрасываем исключение
                throw new UnsupportedOperationException("Method " + methodName + " is not supported for interface: " + interfaceType.getName());
            }
        };

        // Возвращаем прокси-объект, который реализует переданный интерфейс
        return (T) Proxy.newProxyInstance(
                interfaceType.getClassLoader(),
                new Class<?>[]{interfaceType},
                handler
        );
    }
}
