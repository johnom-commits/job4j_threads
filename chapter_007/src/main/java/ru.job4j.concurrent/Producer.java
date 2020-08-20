package ru.job4j.concurrent;

public class Producer implements Runnable {
    private SimpleBlockingQueue<Integer> queue;
    private final int MAX_NUMBERS = 6;

    public Producer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < MAX_NUMBERS; i++) {
            queue.offer(i);
        }
    }
}