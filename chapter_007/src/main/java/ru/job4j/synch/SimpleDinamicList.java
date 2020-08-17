package ru.job4j.synch;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleDinamicList<E> implements Iterable<E> {

    private static final int DEFAULT_CAPACITY = 5;
    private Object[] container;
    private int size = 0;
    private int modCount = 0;

    public SimpleDinamicList() {
        container = new Object[DEFAULT_CAPACITY];
    }

    public void add(E value) {
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length + 5);
        }
        container[size] = value;
        size++;
        modCount++;
    }

    public E get(int index) {
        return (E) container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<E> {
        int index = 0;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return index < size;
        }

        @Override
        public E next() {
            if (index == size) {
                throw new NoSuchElementException("Достигнут конец массива");
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            index++;
            return (E) container[index - 1];
        }
    }
}
