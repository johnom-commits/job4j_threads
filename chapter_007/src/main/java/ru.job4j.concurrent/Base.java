package ru.job4j.concurrent;

public class Base {
    private final int id;
    private String name;
    private int version;

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
        this.version = 1;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public int incrementVersion() {
        return version++;
    }
}
