package ru.job4j.concurrent;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;

public class CacheTest {
    private Cache cache = new Cache();

    @Before
    public void createModelAndPutInCache() {
        Base model = new Base(1, "Model_1");
        cache.add(model);
    }

    @Test
    public void whenUpdateModelInTwoThreads() throws InterruptedException {
        AtomicReference<OptimisticException> ex = new AtomicReference<>();

        Thread thread = new Thread(
                () -> {
                    try {
                        Base model = cache.get(1);
                        updateBase(model, "Supermodel");
                        cache.update(model);
                    } catch (OptimisticException e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();

        Thread thread2 = new Thread(
                () -> {
                    try {
                        Base model = cache.get(1);
                        updateBase(model, "Super");
                        cache.update(model);
                    } catch (OptimisticException e) {
                        ex.set(e);
                    }
                }
        );
        thread2.start();
        thread.join();
        thread2.join();
        assertEquals("The model is already updated", ex.get().getMessage());
    }

    private void updateBase(Base model, String supermodel) {
        model.setName(supermodel);
        model.incrementVersion();
    }

}