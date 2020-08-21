package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CAScount<T> {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CAScount() {
        count.set(0);
    }

    public void increment() {
        int newValue;
        int expect;
        do {
            expect = get();
            newValue = expect + 1;
        } while (!count.compareAndSet(expect, newValue));
    }

    public int get() {
       return count.get();
    }
}
