package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 19.11.2018
 */
public class Node<E extends Comparable<E>> {

    private final E value;
    private final List<Node<E>> children = new ArrayList<>();

    public Node(final E value) {
        this.value  = value;
    }

    public void add(Node<E> child) {
        this.children.add(child);
    }

    public E getValue() {
        return value;
    }

    public List<Node<E>> leaves() {
        return this.children;

    }

    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }
}