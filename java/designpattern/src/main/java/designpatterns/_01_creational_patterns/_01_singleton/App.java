package designpatterns._01_creational_patterns._01_singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

public class App {

    public static void main(String[] args) throws Exception {
        reflection();
        serialize();
    }

    public static void reflection() throws Exception {
        Settings4 settings1 = Settings4.getInstance();

        Constructor<Settings4> constructor = Settings4.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Settings4 settings2 = constructor.newInstance();

        System.out.println(settings1 == settings2);
    }

    public static void serialize() throws Exception {
        Settings4 settings1 = Settings4.getInstance();

        Settings4 settings2;
        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("settings.obj"))) {
            out.writeObject(settings1);
        }

        try (ObjectInput in = new ObjectInputStream(new FileInputStream("settings.obj"))) {
            settings2 = (Settings4) in.readObject();
        }

        System.out.println(settings1 == settings2);
    }
}
