package ru.job4j.bomberman;

import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 18.06.2019
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
     * Default number of monsters.
     */
    private final static int DEFAULT_MONSTERS = 2;

    /**
     * The size of the playing field, which is not set by default.
     */
    private final int size;

    /**
     * The length of time to occupy a new cell.
     */
    private final long occupyTime;

    /**
     * Duration of one move, which is not set by default.
     */
    private final int stepTime;

    /**
     * Playing side.
     */
    private final Player player;

    /**
     * This is a set for storing monsters.
     */
    private final Set<Monster> enemies;

    /**
     * The playing field.
     */
    private final Cell[][] field;

    /**
     * Queue to store the player's movements.
     */
    private BlockingQueue<Direction> movements;

    /**
     * The status of the game.
     * Used by player threads to check the work cycle.
     */
    private volatile boolean gameStatus;

    /**
     * Constructor to create a new playing field.
     *
     * @param size given size
     */
    public Board(int size, int stepTime, int monsters, long occupyTime) {
        this.size = size;
        this.stepTime = stepTime;
        this.occupyTime = occupyTime;
        this.gameStatus = true;
        this.field = new Cell[size][size];
        this.movements = new LinkedBlockingQueue<>();
        this.player = new Hero(this, "Hero", movements);
        this.enemies = IntStream.range(0, monsters)
                .mapToObj(i -> new Monster(this, String.format("Beast %s", i)))
                .collect(Collectors.toCollection(CopyOnWriteArraySet::new));
    }

    /**
     * Constructor to create a new playing field by default.
     */
    public Board() {
        this(DEFAULT_SIZE, DEF_STEP_TIME, DEFAULT_MONSTERS, OCCUPY_TIME);
    }

    /**
     * The method for obtaining the status of the game.
     *
     * @return the status of the game
     */
    public boolean checkGame() {
        return gameStatus;
    }

    /**
     * This method returns the speed of one move.
     *
     * @return the speed of one move
     */
    public int getStepTime() {
        return stepTime;
    }

    /**
     * This is the basic method to run the game.
     * Starts the filling of the field and the work of the playing side.
     * The method also creates locked cells, the number of which depends on the size of the field.
     */
    public void start() {
        IntStream.range(0, size).forEach(
                i -> IntStream.range(0, size).forEach(
                        j -> field[i][j] = new Cell(i, j)
                )
        );
        installBlocks(size / 2);
        player.start();
        enemies.forEach(Monster::start);
    }

    /**
     * The creation of blocked cells.
     *
     * @param blocks the number of locked cells
     */
    private void installBlocks(int blocks) {
        IntStream.range(0, blocks).forEach(i -> startingPosition());
    }

    /**
     * The method adds new directions for making a step.
     *
     * @param target direction of movement
     */
    public void loadNewMove(Direction target) {
        movements.add(target);
    }

    /**
     * Method to stop the flow of players.
     * It is called when the game ends.
     */
    public void stop() {
        synchronized (this) {
            gameStatus = false;
        }
        //player.interrupt();
        //enemies.forEach(Thread::interrupt);
    }

    /**
     * This method implements the transition from the current cell of the playing field to the next.
     * This unlocks the current cell and locks the new one.
     *
     * @param source current cell
     * @param target cell to make a move on it
     * @return boolean values that represent the result of a move
     */
    public boolean move(Cell source, Cell target) throws InterruptedException {
        boolean isLock;
        ReentrantLock moveLock = field[target.x][target.y].lock;
        isLock = moveLock.tryLock() || moveLock.tryLock(occupyTime, TimeUnit.MILLISECONDS);
        if (isLock) {
            crossChecking(source);
            field[source.x][source.y].lock.unlock();

        }
        return isLock && source != target;
    }

    /**
     * The method randomly selects the direction to make a move.
     * The cell for making a move will be adjacent to the current one.
     * This is where you check to ensure that you do not go out of the field.
     * If necessary, the selection cycle is repeated.
     *
     * @param current the cell from which the move is made
     * @return the cell that is selected to make the move
     */
    public Cell selectCell(Cell current) {
        Cell result;
        do {
            Direction[] directions = Direction.values();
            Direction target = directions[ThreadLocalRandom.current().nextInt(directions.length)];
            result = cellToMove(current, target);
        } while (result == current);
        return result;
    }

    /**
     * This method returns the cell in the specified direction.
     *
     * @param current the cell from which the move is made
     * @param target  direction of movement
     * @return the cell in the specified direction
     */
    public Cell cellToMove(Cell current, Direction target) {
        int x = current.x + target.deltax;
        int y = current.y + target.deltay;
        return !checkOutField(x, y) ? field[x][y] : current;
    }

    /**
     * The method of checking the exit from the playing field.
     *
     * @param x horizontal coordinate
     * @param y vertical coordinate
     * @return <tt>true</tt>  in the case where the coordinates do not match coordinates in the field of play
     */
    private boolean checkOutField(int x, int y) {
        return (x < 0 || y < 0 || x >= size || y >= size);
    }

    /**
     * The method determines the player's starting cell by random selection.
     * A free cell is selected and locked by the player.
     *
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
     * The method checks for an attempt to capture the current position by another player.
     * This means that our hero met a monster and the game ends.
     *
     * @param current the occupied cell of the field
     */
    public void crossChecking(Cell current) {
        boolean cross = Thread.currentThread() == player
                ? current.lock.hasQueuedThreads() : current.lock.hasQueuedThread(player);
        if (cross) {
            synchronized (this) {
                gameStatus = false;
            }
        }
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