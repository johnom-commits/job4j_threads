package ru.job4j.net;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Wget {
    private static final int LENGTH_ARGS = 2;

    public static void main(String[] args) throws Exception {
        Wget wget = new Wget();
        wget.checkArgs(args);
        wget.get(args);
    }

    private void checkArgs(String[] args) {
        if (args.length < LENGTH_ARGS) {
            throw new IllegalArgumentException("Указаны не все аргументы командной строки");
        }
    }

    public void get(String[] args) throws Exception {
        InputStream address = new URL(args[0]).openStream();
        long rateLimit = Integer.parseInt(args[1]) * 1024;  // переводим в байты
        try (var in = new BufferedInputStream(address);
             var out = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            while (true) {
                long start = System.nanoTime();
                int bytesRead = in.read(dataBuffer, 0, 1024);
                if (bytesRead == -1) {
                    break;
                }
                out.write(dataBuffer, 0, bytesRead);
                long elapsedTime = System.nanoTime() - start;
                Thread.sleep(getDelay(elapsedTime, rateLimit));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long getDelay(long elapsedTime, long rateLimit) {
        double elapsedTimeInSec = (double) elapsedTime / 1_000_000_000;
        double rate = 1024 / elapsedTimeInSec;   // байт в секунду
        double diff = 1 - (rateLimit / rate);
        return  (long) diff * 1000;  // перевод секунд в миллисекунды
    }
}
