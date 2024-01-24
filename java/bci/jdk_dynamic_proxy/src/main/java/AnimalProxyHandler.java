import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AnimalProxyHandler implements InvocationHandler {

    Object target;

    public AnimalProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("****before****");

        if(method.getName().equals("eat")) {
            System.out.println("----eat 메서드 호출 전----");

            result = method.invoke(target, args); // 메서드 호출

            System.out.println("----eat 메서드 호출 후----");
        } else if(method.getName().equals("drink")) { // 메서드가 drink 라면
            System.out.println("----drink 메서드 호출 전----");

            result = method.invoke(target, args); // 메서드 호출

            System.out.println("----drink 메서드 호출 후----");
        } else {
            throw new NoSuchMethodException();
        }

        System.out.println("****after****");
        return result; // 호출 결과 반환
    }
}