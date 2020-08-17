package ru.job4j.synch;

import net.jcip.annotations.ThreadSafe;
import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    private SimpleDinamicList<T> list = new SimpleDinamicList<>();

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(list).iterator();
    }

    private synchronized SimpleDinamicList<T> copy(SimpleDinamicList<T> list) {
        var listCopy = new SimpleDinamicList<T>();
        list.forEach(listCopy::add);
        return listCopy;
    }
}
