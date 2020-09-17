package ru.job4j.multithreads;

public class Switcher {
    public static void main(String[] args) {
        var barrier = new MasterSlaveBarrier();

        var first = new Thread(
                () -> {
                    while (true) {
                        barrier.tryMaster();
                        System.out.println("Thread A");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        barrier.doneMaster();
                    }
                });

        var second = new Thread(
                () -> {
                    while (true) {
                        barrier.trySlave();
                        System.out.println("Thread B");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        barrier.doneSlave();
                    }
                });

        first.start();
        second.start();
    }
}
