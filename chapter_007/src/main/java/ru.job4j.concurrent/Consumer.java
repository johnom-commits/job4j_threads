package ru.job4j.concurrent;

import java.util.ArrayList;
import java.util.List;

public class Consumer implements Runnable {
    private SimpleBlockingQueue<Integer> queue;
    private List<Integer> list = new ArrayList<>();

    public Consumer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            list.add(queue.poll());
        }
    }

    public List<Integer> getList() {
        return list;
    }
}
