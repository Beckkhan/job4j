package ru.job4j.bomberman;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 15.06.2019
 */
public class Player extends Thread {
    /**
     * The playing field on which the movement of the player will occur.
     */
    private final Board board;
    /**
     * Constructor to create a player and associate it with the playing field.
     * @param board the playing field
     */
    public Player(Board board) {
        this.board = board;
    }
    /**
     * Overriding the run method that performs the player's movement.
     */
    @Override
    public void run() {
        Board.Cell start = board.startingPosition();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Board.Cell next;
                do {
                    next = board.selectCell(start);
                } while (!board.move(start, next));
                start = next;
                Thread.sleep(board.getStepTime());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}