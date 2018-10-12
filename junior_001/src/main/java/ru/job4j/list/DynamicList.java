package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 16.09.2018
 */
public class DynamicList<E> implements Iterable<E> {

    private Object[] container = new Object[10];
    private int position = 0;
    private int modCount = 0;

    public void checkSize() {
        if (position == container.length) {
            int newsize = 2 * container.length;
            Object[] newContainer = new Object[newsize];
            System.arraycopy(container, 0, newContainer, 0, container.length);
            container = newContainer;
        }
    }

    public void add(E value) {
        checkSize();
        container[position++] = value;
        modCount++;
    }

    public E get(int index) {
        return (E) container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            int index = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < position;
            }
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) container[index++];
            }
        };
    }
}
