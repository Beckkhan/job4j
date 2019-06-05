package ru.job4j.pool;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 05.06.2019
 */
public class EmailNotificationTest {

    private AtomicInteger counter = new AtomicInteger();

    EmailNotification sender = new EmailNotification() {
        @Override
        public void send(String subject, String body, String email) {
            super.send(subject, body, email);
            counter.getAndIncrement();
        }
    };

    @Test
    public void test() throws InterruptedException {
        Runnable task = () -> {
            IntStream.range(1, 11).mapToObj(
                    i -> new User(
                            String.format("User No.%d.", i),
                            String.format("user_%d@gmail.com", i)
                    )
            ).forEach(user -> {
                String message = String.format(
                        "Sending a notification to the %s by %s.",
                        user.getName(),
                        user.getEmail()
                );
                System.out.println(message);
                sender.emailTo(user);
            });
        };
        Thread first = new Thread(task);
        Thread second = new Thread(task);
        first.start();
        second.start();
        first.join();
        second.join();
        sender.close();
        while (!sender.workCompleted()) {
            Thread.sleep(500);
        }
        assertFalse(sender.emailTo(new User("Last", "last@gmail.com")));
        assertThat(counter.get(), is(20));
    }
}