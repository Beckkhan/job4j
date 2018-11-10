package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 10.11.2018
 */
public class MyHashMap<K, V> implements Iterable<MyHashMap.Node> {

    /**
     * Класс, представляющий отдельный элемент.
     */
    static class Node<K, V> {
        K key;
        V value;
        int hashCode;

        /**
         * Конструктор для создания экземпляра класса элемента.
         */
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Переопределим методы equals() и hashCode().
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<K, V> node = (Node<K, V>) o;
            return Objects.equals(key, node.key)
                    && Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 37 * result + this.key.hashCode();
            return result;
        }
    }

    /**
     * Создаем хранилище для элементов.
     */
    private Node[] store;

    /**
     * Стартовое количество бакетов в хранилище будет равно 8.
     */
    private final static int START_CAPACITY = 8;

    public MyHashMap() {
        this.store = new Node[START_CAPACITY];
    }

    /**
     * Используем Load Factor при заполненности массива на 75%.
     */
    private static final double LOADFACTOR = 0.75;

    /**
     * Количество реальных элементов в хранилище.
     */
    private int amount = 0;

    /**
     * Переменная, фиксирующая количество изменений коллекции.
     */
    private int modCount = 0;

    private int hash(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % this.store.length;
    }

    /**
     * Метод для определения места для элемента в HashMap, т.е.
     * для расчета индекса.
     */
    private int setIndex(K key) {
        int preIndex = hash(key);
        return preIndex % store.length;
    }

    /**
     * Проверка наличия свободных бакетов для добавления новых элементов.
     * Используется уже проинициализированный выше показатель Load Factor.
     */
    private void checkSize() {
        if (amount > (this.store.length * LOADFACTOR)) {
            int newSize = this.store.length << 1;
            Node[] newStore = new Node[newSize];
            for (Node node : this) {
                newStore[setIndex((K) node.key)] = node;
            }
            this.store = newStore;
        }
    }

    /**
     * Этот метод нам пригодится в тестировании.
     * Возвращает количество реальных элементов в хранилище.
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Метод для добавления новых элементов в коллекцию.
     */
    boolean insert(K key, V value) {
        checkSize();
        boolean result = false;
        Node<K, V> newElement = new Node<>(key, value);
        int index = setIndex(newElement.key);
        if (this.store[index] == null) {
            this.store[index] = newElement;
            this.amount++;
            this.modCount++;
            result = true;
        }
        return result;
    }

    /**
     * Метод получения значения элемента в коллекции по ключу.
     */
    V get(K key) {
        int index = setIndex(key);
        return (this.store[index] != null)
                ? (V) this.store[index].value
                : null;
    }

    /**
     * Метод для удаления значения элемента в коллекции по ключу.
     */
    boolean delete(K key) {
        boolean result = false;
        int index = setIndex((K) key);
        if (this.store[index] != null) {
            this.store[index] = null;
            this.amount--;
            this.modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<Node> iterator() {
        return new Iterator<Node>() {
            int pointer = 0;
            int innerModCount = modCount;
            @Override
            public boolean hasNext() {
                boolean result = false;
                if (innerModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = pointer; i < store.length; i++) {
                    if (store[i] != null) {
                        pointer = i;
                        result = true;
                        break;
                    }
                }
                return result;
            }

            @Override
            public Node next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return store[pointer++];
            }
        };
    }
}