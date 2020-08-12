package ru.job4j.concurrent;

public class DCLSingleton {
    // После первой проверки if (inst == null)
    // переменная inst может попасть в кеш,
    // поэтому при повторной проверке if (inst == null)
    // данные могут считываться уже из кеша, а они к этому времени
    // могут уже устареть - другой поток может инициализировать переменную inst.
    // Ключевое слово volatile гарантирует, что данные переменной актуальны.
    private static volatile DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }

    private DCLSingleton() {
    }
}
