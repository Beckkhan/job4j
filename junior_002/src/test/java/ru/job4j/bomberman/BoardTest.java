package ru.job4j.bomberman;

import org.junit.Test;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 18.06.2019
 */
public class BoardTest {

    private void init(int size, int monsters, int speed, boolean playerApproved, int counter) throws InterruptedException {
        Map<Boolean, Integer> map = new ConcurrentHashMap<>();
        Board board = new Board(size, speed, monsters, speed / 2) {
            @Override
            public boolean move(Cell source, Cell target) throws InterruptedException {
                boolean result = super.move(source, target);
                map.compute(result, (k, v) -> v == null ? 0 : ++v);
                return result;
            }
        };

        Thread mock = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        if (playerApproved) {
                            board.loadNewMove(
                                    Direction.values()[ThreadLocalRandom.current().nextInt(4)]
                            );
                        }
                        try {
                            Thread.sleep(speed / 3);
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        long start = System.currentTimeMillis();
        mock.start();
        Thread control = new Thread(board::start);
        control.start();
        while (board.checkGame()) {
            if (!map.containsKey(false)
                    || map.get(false) >= counter
                    || (!playerApproved || map.containsKey(true) || size == 1)
                    ) {
                control.interrupt();
                mock.interrupt();
                break;
            }
        }
        long total = System.currentTimeMillis() - start;
        long dif = 50L;
        assertThat(total,
                lessThan(dif + speed / 2 * map.getOrDefault(false, 0) + speed * map.getOrDefault(true, 0))
        );
    }

    @Test
    public void whenTest() throws InterruptedException {
        init(10, 50, 20, true, 10);
    }

    @Test
    public void whenTestOnceAgain() throws InterruptedException {
        init(10, 10, 50, false, 100);
    }

    @Test
    public void whenSmallBoard() throws InterruptedException {
        init(1, 10, 0, true, 10);
    }

    @Test
    public void whenCheckBoardWorking() {
        Board board = new Board();
        board.start();
        board.stop();
        assertThat(board.getStepTime(), is(1000));
    }

    @Test
    public void whenNoAnyFreeCells() throws InterruptedException {
        init(3, 20, 7, false, 10);
    }
}