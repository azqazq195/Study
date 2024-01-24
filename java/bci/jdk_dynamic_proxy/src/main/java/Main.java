import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Animal rabbit = (Animal) Proxy.newProxyInstance(
                Animal.class.getClassLoader(),
                new Class[]{Animal.class},
                new AnimalProxyHandler(new Rabbit())
        );

        Animal tiger = (Animal) Proxy.newProxyInstance(
                Animal.class.getClassLoader(),
                new Class[]{Animal.class},
                new AnimalProxyHandler(new Tiger())
        );

        rabbit.eat();
        System.out.println();
        rabbit.drink();
        System.out.println();

        tiger.eat();
        System.out.println();
        tiger.drink();
        System.out.println();
    }
}
