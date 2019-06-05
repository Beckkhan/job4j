package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 05.06.2019
 */
public class EmailNotification {
    /**
     * A pool of threads.
     */
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );
    /**
     * The method adds to the pool a task to send notifications
     * to the specified user and then executes it.
     * @param user User
     * @return Notification sending status.
     */
    public boolean emailTo(User user) {
        boolean result = false;
        if (!pool.isShutdown()) {
            String subject = String.format("Notification %s to eMail %s.", user.getName(), user.getEmail());
            String body = String.format("Add a new event to %s.", user.getName());
            pool.submit(() -> this.send(subject, body, user.getEmail()));
        }
        return result;
    }
    /**
     * The method sends information to the email.
     * @param subject message header
     * @param body    message body
     * @param eMail   e-mail address
     */
    public void send(String subject, String body, String eMail) { }
    /**
     * The method initiates an ordered shutdown in which previously
     * submitted tasks are executed and new tasks are not accepted.
     */
    public void close() {
        this.pool.shutdown();
        System.out.println("The pool is already closed.");
    }
    /**
     * The method reflects the state of the pool after a call to stop (shutdown).
     * @return Task completion status.
     */
    public boolean workCompleted() {
        return pool.isTerminated();
    }
}