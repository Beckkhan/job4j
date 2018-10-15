package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 15.10.2018
 */
public class CycleTest {

    Cycle cycle = new Cycle();
    Node first = new Node(1);
    Node second = new Node(2);
    Node third = new Node(3);
    Node fourth = new Node(4);
    Node fifth = new Node(5);

    @Test
    public void whenCheckCycleInListAndHaveLoop() {
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = third;
        assertThat(cycle.hasCycle(first), is(true));
    }
    @Test
    public void whenCheckCycleInListAndNoLoop() {
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = null;
        assertThat(cycle.hasCycle(first), is(false));
    }
}