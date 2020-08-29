package ru.job4j.concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class ThreadPool {
    private final int numberProc = Runtime.getRuntime().availableProcessors();
    private final List<Thread> poolThreads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> queue = new SimpleBlockingQueue<>(3);
    private volatile boolean isRunning = true;

    public ThreadPool() {
        fillPool();
        poolThreads.forEach(Thread::start);
    }

    private void fillPool() {
        Runnable runnable = () -> {
            while (isRunning || !queue.isEmpty()) {
                queue.poll().run();
            }
        };
        Stream.generate(() -> new Thread(runnable)).limit(numberProc).forEach(poolThreads::add);
    }

    public void work(Runnable job) {
        if (isRunning) {
            queue.offer(job);
        }
    }

    public void shutdown() {
        isRunning = false;
    }
}
