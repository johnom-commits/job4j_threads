package ru.job4j.concurrent;

public class CountBarrierMain {
    public static void main(String[] args) throws InterruptedException {

        CountBarrier count = new CountBarrier(2);

        Thread first = new Thread(() -> {
            System.out.println("first");
            count.await();
        });
        Thread second = new Thread(() -> {
            System.out.println("second");
            count.await();
        });

        first.start();
        second.start();
        count.count();
        count.count();
        first.join();
        second.join();
    }
}
