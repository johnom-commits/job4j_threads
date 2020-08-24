package ru.job4j.concurrent;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest2 {
    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final List<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);

        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 9).forEach(queue::offer);
                    queue.offer(-1);
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            int x = queue.poll();
                            if (x == -1) {
                                break;
                            }
                            buffer.add(x);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertEquals(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8), buffer);
    }
}