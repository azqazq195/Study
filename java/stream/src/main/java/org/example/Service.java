package org.example;

import java.util.ArrayList;
import java.util.List;

public class Service {

    public List<Entity> createEntities() {
        List<Entity> result = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            result.add(new Entity(i, "name" + i));
        }
        return result;
    }
}
