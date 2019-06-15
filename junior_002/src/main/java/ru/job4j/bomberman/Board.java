package ru.job4j.bomberman;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 15.06.2019
 */
public class Board {
    /**
     * Default time to occupy a new cell.
     */
    private final static long OCCUPY_TIME = 500;

    /**
     * Default duration of one move.
     */
    private final static int DEF_STEP_TIME = 1000;

    /**
     * Default playing field size.
     */
    private final static int DEFAULT_SIZE = 10;

    /**
     * The size of the playing field, which is not set by default.
     */
    private final int size;

    /**
     * Duration of one move, which is not set by default.
     */
    private final int stepTime;

    /**
     * Playing side.
     */
    private final Player player;

    /**
     * The playing field.
     */
    private final Cell[][] field;

    /**
     * This boolean flag determines that the game starts over.
     * Used to verify that the field must be cleared before filling in.
     */
    private boolean firstStart  = true;

    /**
     * Constructor to create a new playing field.
     * @param size given size
     */
    public Board(int size, int stepTime) {
        this.size = size;
        this.stepTime = stepTime;
        this.field = new Cell[size][size];
        this.player = new Player(this);
    }

    /**
     * Constructor to create a new playing field by default.
     */
    public Board() {
        this(DEFAULT_SIZE, DEF_STEP_TIME);
    }

    /**
     * This method returns the speed of one move.
     * @return the speed of one move
     */
    public int getStepTime() {
        return stepTime;
    }

    /**
     * This is the basic method to run the game.
     * Starts the filling of the field and the work of the playing side..
     */
    public void init() {
        this.reset();
        player.start();
        this.firstStart = false;
    }

    /**
     * This method sets a new playing field according to the specified size.
     */
    public void reset() {
        if (!firstStart) {
            this.stop();
        }
        IntStream.range(0, size).forEach(
                i -> IntStream.range(0, size).forEach(
                        j -> field[i][j] = new Cell(i, j)
                )
        );
    }

    /**
     * Method to stop the flow of players.
     * It is called when the game ends.
     */
    public void stop() {
        player.interrupt();
        firstStart = true;
    }

    /**
     * This method implements the transition from the current cell of the playing field to the next.
     * This unlocks the current cell and locks the new one.
     * @param source current cell
     * @param target cell to make a move on it
     * @return boolean values that represent the result of a move
     */
    public boolean move(Cell source, Cell target) throws InterruptedException {
        boolean isLock;
        ReentrantLock moveLock = field[target.x][target.y].lock;
        isLock = moveLock.tryLock() || moveLock.tryLock(OCCUPY_TIME, TimeUnit.MILLISECONDS);
        if (isLock) {
            field[source.x][source.y].lock.unlock();
        }
        return isLock;
    }

    /**
     * The method randomly selects the direction to make a move.
     * The cell for making a move will be adjacent to the current one.
     * This is where you check to ensure that you do not go out of the field.
     * If necessary, the selection cycle is repeated.
     * @param current the cell from which the move is made
     * @return the cell that is selected to make the move
     */
    public Cell selectCell(Cell current) {
        int x = current.x;
        int y = current.y;
        do {
            double num = ThreadLocalRandom.current().nextDouble();
            if (num < 0.5) {
                x += num < 0.25 ? 1 : -1;
            } else {
                y += num < 0.75 ? 1 : -1;
            }
        } while (x < 0 || y < 0 || x >= size || y >= size);
        return field[x][y];
    }

    /**
     * The method determines the player's starting cell by random selection.
     * A free cell is selected and locked by the player.
     * @return the starting cell of the player
     */
    public Cell startingPosition() {
        Cell result;
        do {
            int i = ThreadLocalRandom.current().nextInt(size);
            int j = ThreadLocalRandom.current().nextInt(size);
            result = field[i][j];
        } while (result.lock.isLocked());
        result.lock.lock();
        return result;
    }

    /**
     * Class to describe the playing field cell.
     */
    public static class Cell {
        private final int x;
        private final int y;

        private final ReentrantLock lock;

        private Cell(int x, int y) {
            this.x = x;
            this.y = y;
            this.lock = new ReentrantLock(true);
        }
    }
}