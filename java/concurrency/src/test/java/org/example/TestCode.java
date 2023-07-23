package org.example;

import org.junit.jupiter.api.RepeatedTest;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class TestCode {

    @RepeatedTest(10)
    public void resultEntity() throws InterruptedException {
        System.out.println("Entity: " + entityTest());
    }

    @RepeatedTest(10)
    public void resultSynchronizedEntityTest() throws InterruptedException {
        System.out.println("Synchronized: " + synchronizedEntityTest());
    }

    @RepeatedTest(10)
    public void resultLockedEntityTest() throws InterruptedException {
        System.out.println("LockedEntity: " + lockedEntityTest());
    }

    @RepeatedTest(10)
    public void resultAtomicEntityTest() throws InterruptedException {
        System.out.println("AtomicEntity: " + atomicEntityTest());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void benchmarkEntity() throws InterruptedException {
        entityTest();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void benchmarkSynchronizedEntityTest() throws InterruptedException {
        synchronizedEntityTest();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void benchmarkLockedEntityTest() throws InterruptedException {
        lockedEntityTest();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void benchmarkAtomicEntityTest() throws InterruptedException {
        atomicEntityTest();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public int entityTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Entity entity = new Entity();

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 10000; j++) {
                    entity.increment();
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);

        return entity.getNum();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public int synchronizedEntityTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        SynchronizedEntity synchronizedEntity = new SynchronizedEntity();

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 10000; j++) {
                    synchronizedEntity.increment();
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);

        return synchronizedEntity.getNum();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public int lockedEntityTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        LockedEntity lockedEntity = new LockedEntity();

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 10000; j++) {
                    lockedEntity.increment();
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);

        return lockedEntity.getNum();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public int atomicEntityTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        AtomicEntity atomicEntity = new AtomicEntity();

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 10000; j++) {
                    atomicEntity.increment();
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);

        return atomicEntity.getNum();
    }
}
