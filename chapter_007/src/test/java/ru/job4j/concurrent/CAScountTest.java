package ru.job4j.concurrent;

import org.junit.Test;
import static org.junit.Assert.*;

public class CAScountTest {
    @Test
    public void whenIncrementValue() {
        CAScount<Integer> count = new CAScount<>();
        count.increment();
        assertEquals(1, count.get());
    }

    @Test
    public void whenHaveTwoThreadsForIncrement() throws InterruptedException {
        CAScount<Integer> count = new CAScount<>();
        Runnable r = () -> {
                count.increment();
        };
        Thread first = new Thread(r);
        Thread second = new Thread(r);
        first.start();
        second.start();
        first.join();
        second.join();
        assertEquals(2, count.get());
    }
}