package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;

@ThreadSafe
public class Cache {
    Map<Integer, Base> map = new ConcurrentHashMap<>();

    public void add(Base model) {
        map.put(model.getId(), model);
    }

    public void update(Base model) {
        int versionCache = map.get(model.getId()).getVersion();
        AtomicInteger version = new AtomicInteger(versionCache);
        int expected = version.incrementAndGet();

        BiFunction<Integer, Base, Base> biFun = (i, m) -> {
            if (expected == model.getVersion()) {
                return model;
            } else {
                throw new OptimisticException("The model is already updated");
            }
        };
        map.computeIfPresent(model.getId(), biFun);
    }

    public void delete(Base model) {
        map.remove(model.getId(), model);
    }

    public Base get(int id) {
        return map.get(id);
    }
}
