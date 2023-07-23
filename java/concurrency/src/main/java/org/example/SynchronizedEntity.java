package org.example;

public class SynchronizedEntity extends Entity {

    public SynchronizedEntity() {
        super();
    }

    @Override
    public synchronized void increment() {
        super.increment();
    }
}
