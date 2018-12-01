package ru.job4j.tree;

import java.util.*;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 24.11.2018
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Корневой элемент.
     */
    public final Node<E> root;

    /**
     * Счетчик изменений.
     */
    private int modCount;

    public Tree(E value) {
        this.root = new Node<E>(value);
    }

    /**
     * Метод поиска элементов в дереве.
     * @param value parent
     * @return child
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.eqValue(value)) {
                rsl = Optional.of(element);
                break;
            }
            for (Node<E> child : element.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Метод для добавления элемента в дерево.
     * @param parent
     * @param child
     * @return
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> parentNode = this.findBy(parent);
        if (!this.findBy(child).isPresent() && parentNode.isPresent()) {
            parentNode.get().add(new Node<>(child));
            modCount++;
            result = true;
        }
        return result;
    }

    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element != null && element.leaves().size() > 2) {
                result = false;
                break;
            }
            for (Node<E> child : element.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Queue<Node<E>> data = new LinkedList<>();
            private int expectedModCount;

            {
                this.data.offer(root);
                this.expectedModCount = modCount;
            }

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return !data.isEmpty();
            }
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Can`t find element!");
                }
                Node<E> element = data.poll();
                for (Node<E> child : element.leaves()) {
                    data.offer(child);
                }
                return element.getValue();
            }
        };
    }
}