package ru.job4j.list;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 15.10.2018
 */
public class Node<T> {
    T value;
    Node<T> next;

    Node(T value) {
        this.value = value;
    }
}