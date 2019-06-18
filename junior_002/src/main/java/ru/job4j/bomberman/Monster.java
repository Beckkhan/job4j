package ru.job4j.bomberman;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 18.06.2019
 */
public class Monster extends Player {
    /**
     * Constructor to create a player Monster and associate it with the playing field.
     *
     * @param board the playing field
     * @param name the name of the thread
     */
    public Monster(Board board, String name) {
        super(board, name);
    }

    /**
     * The method contains the logic of making a move by the Monster.
     * The method returns the cell on which the move is made.
     *
     * @param start current position
     * @return the cell on which the move is made
     * @throws InterruptedException in case of thread interruption during the player's turn
     */
    @Override
    public Board.Cell makeStep(Board.Cell start) throws InterruptedException {
        Board.Cell result;
        do {
            result = board.selectCell(start);
        } while (!board.move(start, result));
        Thread.sleep(board.getStepTime());
        return result;
    }
}