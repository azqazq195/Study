package stream;

public class Entity {

    private int id;
    private String name;

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Entity{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
