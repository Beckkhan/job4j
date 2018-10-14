package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 14.10.2018
 */
public class DynamicContainer<E> implements Iterable<E> {

    private Node<E> first = new Node<>(null);
    private int position = 0;
    private int modCount = 0;

    /**
     * Метод вставки данных в начало списка.
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.position++;
        this.modCount++;
    }

    /**
     * Метод удаления первого элемента в списке.
     */
    public E delete() {
        Node<E> result = this.first;
        this.first = result.next;
        this.position--;
        return result.data;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        if (index >= position || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.position;
    }

    /**
     * Класс для хранения данных.
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    /**
     * Реализация итератора.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> second = first;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return second.next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = second.data;
                second = second.next;
                return result;
            }
        };
    }
}
