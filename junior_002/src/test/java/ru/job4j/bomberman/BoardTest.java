package ru.job4j.bomberman;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicInteger;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 15.06.2019
 */
public class BoardTest {
    @Test
    public void boardTest() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();
        Board board = new Board(10, 10) {
            @Override
            public boolean move(Cell source, Cell dist) throws InterruptedException {
                boolean result = super.move(source, dist);
                if (counter.incrementAndGet() == 10) {
                    Thread.currentThread().interrupt();
                }
                return result;
            }
        };
        long start = System.currentTimeMillis();
        board.init();
        while (counter.get() != 10) {
            Thread.sleep(100);
        }
        board.stop();
        long total = System.currentTimeMillis() - start;
        assertThat(total, lessThan(250L));
    }
}