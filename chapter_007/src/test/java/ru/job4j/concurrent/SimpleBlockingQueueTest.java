package ru.job4j.concurrent;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
public class SimpleBlockingQueueTest {

    @Test
    public void whenWorkOneProducerAndOneConsumer() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        Thread prod = new Thread(producer);
        Thread cons = new Thread(consumer);

        prod.start();
        cons.start();
        prod.join();
        cons.join(10000);
        List<Integer> result = consumer.getList();
        assertEquals(List.of(0, 1, 2, 3, 4, 5), result);
    }
}