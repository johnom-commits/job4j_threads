package ru.job4j.io;

import java.io.*;

public class ParseFile {
    private volatile File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() {
        String output = "";
        try (var i = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = i.read()) > 0) {
                output += (char) data;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public synchronized String getContentWithoutUnicode() {
        String output = "";
        try (var i = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = i.read()) > 0) {
                if (data < 0x80) {
                    output += (char) data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public synchronized void saveContent(String content) {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
