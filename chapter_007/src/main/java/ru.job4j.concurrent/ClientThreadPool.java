package ru.job4j.concurrent;

public class ClientThreadPool {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();

        Runnable job = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        for (int i = 0; i < 6; i++) {
            pool.work(job);
        }

        pool.shutdown();
    }
}
