package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread first = new Thread(() -> {}, "First thread");
        System.out.println(first.getName() + ": " + first.getState());
        first.start();

        Thread second = new Thread(() -> {}, "Second thread");
        System.out.println(second.getName()  + ": " + second.getState());
        second.start();
        while (first.getState() != Thread.State.TERMINATED) {
            printState(first, second);
        }
        printState(first, second);
        second.join();
        System.out.println("Работа завершена. Все свободны");
    }

    private static void printState(Thread first, Thread second) {
        System.out.println(first.getName() + ": " + first.getState());
        System.out.println(second.getName() + ": " + second.getState());
    }
}
