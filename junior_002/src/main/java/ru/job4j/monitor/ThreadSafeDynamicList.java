package ru.job4j.monitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.*;

import java.util.Iterator;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 13.12.2018
 */
@ThreadSafe
public class ThreadSafeDynamicList<E> implements Iterable<E> {
    @GuardedBy("this")
    private final DynamicList<E> array;

    public ThreadSafeDynamicList() {
        this.array = new DynamicList<>();
    }

    public synchronized void add(E value) {
        this.array.add(value);
    }
    public synchronized E get(int index) {
        return this.array.get(index);
    }

    public synchronized DynamicList<E> copy() {
        DynamicList<E> store = new DynamicList<>();
        for (E value : this.array) {
            store.add(value);
        }
        return store;
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return this.copy().iterator();
    }
}