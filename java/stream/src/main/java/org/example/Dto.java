package org.example;

public class Dto {
    private int id;
    private String name;

    public Dto(Entity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
