package ru.job4j.tree;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 24.11.2018
 */
public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }
    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }
    @Test
    public void whenIterate() {
        Tree<Integer> tree = new Tree<>(7);
        tree.add(7, 3);
        tree.add(7, 5);
        tree.add(7, 1);
        tree.add(3, 2);
        tree.add(5, 9);
        tree.add(1, 4);
        tree.add(1, 8);
        Iterator iterator = tree.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(7));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(9));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(8));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void whenTreeIsBinary() {
        Tree<Integer> tree = new Tree<>(7);
        tree.add(7, 3);
        tree.add(7, 5);
        tree.add(3, 2);
        tree.add(3, 8);
        tree.add(5, 9);
        tree.add(5, 9);
        tree.add(9, 1);
        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void whenTreeIsNotBinary() {
        Tree<Integer> tree = new Tree<>(7);
        tree.add(7, 3);
        tree.add(7, 5);
        tree.add(3, 2);
        tree.add(3, 8);
        tree.add(3, 4);
        tree.add(5, 9);
        tree.add(9, 1);
        assertThat(tree.isBinary(), is(false));
    }
}
