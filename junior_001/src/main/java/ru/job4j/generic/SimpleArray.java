package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 16.09.2018
 */
public class SimpleArray<T> implements Iterable<T> {

    private Object[] values;
    private int position = 0;

    public SimpleArray(int size) {
        this.values = new Object[size];
    }

    public void add(T model) {
        if (!(position < values.length) && position < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.values[position++] = model;
    }

    public void set(int index, T model) {
        if (index < 0 && index >= this.position) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.values[index] = model;
    }

    public void delete(int index) {
        if (index < 0 && index >= this.values.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        System.arraycopy(this.values, index + 1, this.values, index, this.values.length - 1 - index);
        this.values[this.position - 1] = null;
        this.position--;
    }

    public T get(int index) {
        T result = null;
        if (!(index <= this.position) && index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        result = (T) this.values[index];
        return result;
    }

    public int getIndex(T model) {
        int result = -1;
        if (model != null) {
            for (int i = 0; i < values.length; i++) {
                if (values[i] == model) {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int pointer = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;
                for (int i = pointer; i < position; i++) {
                    if (values[i] != null) {
                        pointer = i;
                        result = true;
                        break;
                    }
                }
                return result;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) values[pointer++];
            }
        };
    }
}