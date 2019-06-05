package ru.job4j.pool;

import ru.job4j.waitnotify.SimpleBlockingQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 05.06.2019
 */
public class ThreadPool {
    /**
     * A list to store threads.
     */
    private final List<Thread> threads = new LinkedList<>();
    /**
     * A queue of tasks.
     */
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    private boolean working = true;

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    if (!tasks.isEmpty()) {
                        try {
                            tasks.poll().run();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }
    }
    /**
     * The method adds a new task to the task queue.
     *
     * @param job new task to add
     * @throws InterruptedException thrown when the current thread fails
     */
    public void work(Runnable job) throws InterruptedException {
        if (!working) {
            throw new RejectedExecutionException();
        }
        tasks.offer(job);
    }
    /**
     * The method shuts down the pool.
     */
    public void shutdown() {
        working = false;
        threads.forEach(Thread::interrupt);
    }
}