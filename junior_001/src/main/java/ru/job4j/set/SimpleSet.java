package ru.job4j.set;

import ru.job4j.list.DynamicList;
import java.util.Iterator;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 15.10.2018
 */
public class SimpleSet<E> implements Iterable<E> {
    private DynamicList<E> list = new DynamicList<>();

    public void add(E e) {
        if (!contains(e)) {
            list.add(e);
        }
    }
    private boolean contains(E e) {
        boolean result = false;
        for (E value : list) {
            if (value.equals(e)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public E get(int index) {
        return this.list.get(index);
    }

    @Override
    public Iterator<E> iterator() {
        return this.list.iterator();
    }
}