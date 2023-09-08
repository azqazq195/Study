package callby;

public class Main {

    public static class MyObject {

        int value;
    }

    public static void modifyPrimitive(int x) {
        x = 10;
    }

    public static void modifyReference(MyObject obj) {
        obj.value = 10;
    }

    public static void changeReference(MyObject obj) {
        obj = new MyObject();
        obj.value = 20;
    }

    public static void changeString(String str) {
        str = "changed";
    }

    public static void main(String[] args) {
        int primitive = 5;
        modifyPrimitive(primitive);
        System.out.println(primitive); // 5, 원시 타입은 변경되지 않습니다.

        MyObject myObject = new MyObject();
        myObject.value = 5;
        modifyReference(myObject);
        System.out.println(myObject.value); // 10, 객체의 멤버는 변경되었습니다.
        System.out.println(myObject.hashCode());

        changeReference(myObject);
        System.out.println(myObject.value); // 10, 참조 자체는 변경되지 않았습니다.

        String str = "string";
        changeString(str);
        System.out.println(str); // string, 참조 자체는 변경되지 않았습니다.
    }
}
