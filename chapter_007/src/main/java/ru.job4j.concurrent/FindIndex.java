package ru.job4j.concurrent;

import java.util.concurrent.RecursiveTask;

public class FindIndex<T> extends RecursiveTask<Integer> {
    private final T[] objs;
    private final int start;
    private final int end;
    private final int THRESHOLD = 10;
    private final T obj;

    public FindIndex(T[] objs, T obj) {
        this(objs, obj, 0, objs.length);
    }

    public FindIndex(T[] objs, T obj, int start, int end) {
        this.objs = objs;
        this.start = start;
        this.end = end;
        this.obj = obj;
    }

    @Override
    protected Integer compute() {
        int index = 0;
        if ((end - start) <= THRESHOLD) {
            for (int i = start; i < end; i++) {
                if (objs[i].equals(obj)) {
                    index = i;
                    break;
                }
            }
        } else {
            int middle = (start + end) / 2;
            FindIndex<T> findIndex1 = new FindIndex<>(objs, obj, start, middle);
            FindIndex<T> findIndex2 = new FindIndex<>(objs, obj, middle + 1, end);
            findIndex1.fork();
            findIndex2.fork();
            index = findIndex1.join() + findIndex2.join();
        }
        return index;
    }
}
