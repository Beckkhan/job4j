package ru.job4j.bomberman;

import java.util.concurrent.BlockingQueue;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 18.06.2019
 */
public class Hero extends Player {
    /**
     * Queue to store the player's movements.
     */
    private final BlockingQueue<Direction> movements;
    /**
     * Constructor to create a player Hero and associate it with the playing field.
     * @param board the playing field
     * @param name the name of the thread
     * @param movements the queue to store the player's movements
     */
    public Hero(Board board, String name, BlockingQueue<Direction> movements) {
        super(board, name);
        this.movements = movements;
    }

    /**
     * The method contains the logic of making a move by the Hero.
     * The method returns the cell on which the move is made.
     *
     * @param start current position
     * @return the cell on which the move is made
     * @throws InterruptedException in case of thread interruption during the player's turn
     */
    public Board.Cell makeStep(Board.Cell start) throws InterruptedException {
        Direction target = movements.take();
        Board.Cell next = board.cellToMove(start, target);
        return board.move(start, next) ? next : start;
    }
}