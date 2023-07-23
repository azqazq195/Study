package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class LockedEntity extends Entity {
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void increment() {
        lock.lock();
        try {
            super.increment();
        } finally {
            lock.unlock();
        }
    }
}
