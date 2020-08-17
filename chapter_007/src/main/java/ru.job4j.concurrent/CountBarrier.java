package ru.job4j.concurrent;

public class CountBarrier {
    private final Object monitor = this;
    private final int total;
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public synchronized void count() {
        count++;
        if (total == count) {
            notifyAll();
        }
    }

    public void await() {
        System.out.println("Enter in await");
        synchronized (monitor) {
            while (total != count) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
