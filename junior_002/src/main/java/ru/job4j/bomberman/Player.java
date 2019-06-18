package ru.job4j.bomberman;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 18.06.2019
 */
public abstract class Player extends Thread {
    /**
     * The playing field on which the movement of the player will occur.
     */
    final Board board;
    /**
     * Constructor to create a player and associate it with the playing field.
     * @param board the playing field
     */
    public Player(Board board, String name) {
        super(name);
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
                Board.Cell next = makeStep(start);
                start = next;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * The method contains the logic of making a move by the player.
     * The method returns the cell on which the move is made.
     *
     * @param start current position
     * @return the cell on which the move is made
     * @throws InterruptedException in case of thread interruption during the player's turn
     */
    public abstract Board.Cell makeStep(Board.Cell start) throws InterruptedException;
}