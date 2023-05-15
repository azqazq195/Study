package me.whiteship.designpatterns._01_creational_patterns._01_singleton;

/**
 * eager initialization
 */
public class Settings6 {

    private static final Settings6 INSTANCE = new Settings6();

    //    static {
//        try {
//            INSTANCE = new Settings6();
//        } catch (Exception e) {
//            throw new ExceptionInInitializerError(e);
//        }
//    }

    private Settings6() {
    }

    public static Settings6 getInstance() {
        return INSTANCE;
    }

}