package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicEntity {
    private final AtomicInteger num = new AtomicInteger(0);

    public void increment() {
        this.num.incrementAndGet();
    }

    public int getNum() {
        return this.num.get();
    }
}
