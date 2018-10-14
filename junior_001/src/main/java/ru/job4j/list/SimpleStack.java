package ru.job4j.list;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 14.10.2018
 */

public class SimpleStack<E> {

    private DynamicContainer<E> list = new DynamicContainer<>();
    /**
     * Метод вставляет значение в начало списка.
     */
    public void push(E data) {
        list.add(data);
    }

    /**
     * Метод возвращает значение и удаляет его из коллекции.
     */
    public E poll() {
        return list.delete();
    }
}
