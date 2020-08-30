package ru.job4j.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        Runnable task = () -> {
            String subject = getSubject(user);
            String body = "Add a new event to " + user.getName();
            send(subject, body, user.getEmail());
        };
        pool.submit(task);
    }

    public void send(String subject, String body, String email) {
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String getSubject(User user) {
        return new StringBuilder("Notification ")
                .append(user.getName())
                .append(" to email ")
                .append(user.getEmail()).toString();
    }
}
