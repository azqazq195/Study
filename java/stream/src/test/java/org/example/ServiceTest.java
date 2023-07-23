package org.example;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@State(Scope.Benchmark)
public class ServiceTest {
    private final Service service = new Service();

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void useStream() {
        Entity[] entityArray = service.createEntities().toArray(Entity[]::new);
        List<Dto> dtoList = Arrays.stream(entityArray).map(Dto::new).toList();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void useNotStream() {
        Entity[] entityArray = service.createEntities().toArray(Entity[]::new);
        List<Dto> dtoList = new ArrayList<>();
        for (Entity entity : entityArray) {
            dtoList.add(new Dto(entity));
        }
    }
}