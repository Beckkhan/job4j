package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 19.11.2018
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);
}