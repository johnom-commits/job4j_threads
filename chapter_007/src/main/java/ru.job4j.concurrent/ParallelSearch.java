package ru.job4j.concurrent;

public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(4);

        final Thread consumer = new Thread(
                () -> {
                    while (true) {
                        int val = queue.poll();
                        if (val == -1) {
                            break;
                        }
                        System.out.println(val);
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.offer(-1);
                }

        ).start();
    }
}
