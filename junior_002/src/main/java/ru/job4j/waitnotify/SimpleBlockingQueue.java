package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 3.0
 * @since 05.06.2019
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    /**
     * Очередь для хранения элементов.
     */
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private final int capacity = 10;

    /**
     * Метод добавляет элемент, если очередь пуста.
     * Если в очереди уже есть элементы, выставляем поток в состояние wait().
     */
    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() > capacity) {
            System.out.println("The Queue contains the element. Thread id " + Thread.currentThread().getId());
            wait();
        }
        System.out.println("Add an element " + value + " to the queue. Thread id " + Thread.currentThread().getId());
        this.queue.offer(value);
        notify();
    }

    /**
     * Метод возвращает элемент из очереди, если она не пуста.
     * Если в очереди нет элементов, выставляем поток в состояние wait().
     */
    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("The Queue does not contain any elements. Thread id " + Thread.currentThread().getId());
            wait();
        }
        T result = this.queue.poll();
        System.out.println("The returned element is " + result + ". Thread id " + Thread.currentThread().getId());
        notify();
        return result;
    }

    public synchronized boolean isEmpty() {
        return queue.size() == 0;
    }
}