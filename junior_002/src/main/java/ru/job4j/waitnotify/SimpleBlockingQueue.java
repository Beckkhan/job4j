package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.*;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 04.01.2019
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    /**
     * Очередь для хранения элементов.
     */
    @GuardedBy("this")
    private final SimpleQueue<T> queue = new SimpleQueue<>();
    private boolean block = false;

    /**
     * Метод добавляет элемент, если очередь пуста.
     * Если в очереди уже есть элементы, выставляем поток в состояние wait().
     */
    public synchronized void offer(T value) throws InterruptedException {
        while (this.block) {
            System.out.println("The Queue contains the element. Thread id " + Thread.currentThread().getId());
            wait();
        }
        System.out.println("Add an element " + value + " to the queue. Thread id " + Thread.currentThread().getId());
        this.queue.push(value);
        this.block = true;
        notify();
    }

    /**
     * Метод возвращает элемент из очереди, если она не пуста.
     * Если в очереди нет элементов, выставляем поток в состояние wait().
     */
    public synchronized T poll() throws InterruptedException {
        while (!this.block) {
            System.out.println("The Queue does not contain any elements. Thread id " + Thread.currentThread().getId());
            wait();
        }
        T result = this.queue.poll();
        System.out.println("The returned element is " + result + ". Thread id " + Thread.currentThread().getId());
        this.block = false;
        notify();
        return result;
    }
}