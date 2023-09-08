package concurrency;

public class Entity {
    private int num;

    public void increment() {
        this.num += 1;
    }

    public int getNum() {
        return this.num;
    }
}
