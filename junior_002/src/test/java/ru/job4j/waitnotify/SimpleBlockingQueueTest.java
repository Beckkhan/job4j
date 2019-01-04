package ru.job4j.waitnotify;

import org.junit.Test;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 04.01.2019
 */
public class SimpleBlockingQueueTest {

    /**
     * Класс Producer добавляет элементы.
     */
    class Producer implements Runnable {

        private final SimpleBlockingQueue<Integer> queue;

        public Producer(SimpleBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    queue.offer(i);
                }  catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * Класс Consumer добавляет элементы.
     */
    class Consumer implements Runnable {

        private final SimpleBlockingQueue<Integer> queue;

        public Consumer(SimpleBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    queue.poll();
                }  catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void whenCheckHowQueueWorks() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
