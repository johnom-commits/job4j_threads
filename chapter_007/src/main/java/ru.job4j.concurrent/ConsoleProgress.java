package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                count++;
                System.out.print("\rloading ..." + getStick(count));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private String getStick(int count) {
        int r = count % 3;
        String s = "";
        if (r == 0) {
            s = "\\";
        } else if (r == 1) {
            s = "|";
        } else
            s = "/";
        return s;
    }
}
