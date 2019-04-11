package ru.job4j.controltask;

import ru.job4j.controltask.io.Output;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 10.04.2019
 */
public class Computer implements PlayingSide {

    private Board board;
    private Output output;
    private String name = "Computer";
    private String cellValue;

    /**
     * Constructor for the player, which is a Computer.
     * As parameters takes the playing field Board and the output object.
     * @param board
     * @param output
     */
    public Computer(Board board, Output output) {
        this.board = board;
        this.output = output;
    }

    /**
     * Checks if the cell selected for the move is empty.
     */
    private Cell getCellForMove() {
        for (int i = 0; i < this.board.size(); i++) {
            for (int j = 0; j < this.board.size(); j++) {
                if (this.board.empty(i, j)) {
                    return this.board.showCell(i, j);
                }
            }
        }
        throw new RuntimeException("All cells are occupied");
    }

    /**
     * A method for making a move by a Computer.
     * The method verifies that the coordinates correspond to the field size.
     * If there are empty Cells in the field, it edits the value in the Cell by the accepted coordinates.
     */
    private void makeMove(Cell cell, String cellValue) {
        int x = cell.showX();
        int y = cell.showY();
        if (x >= this.board.size() || y >= this.board.size() || x < 0 || y < 0) {
            throw new RuntimeException("Invalid move: Out of board size.");
        }
        if (this.board.empty(x, y)) {
            this.board.editCell(x, y, cellValue);
        } else {
            throw new RuntimeException("Invalid move: Occupied cell.");
        }
    }

    /**
     * Redefining the method of making a move, which includes auxiliary methods.
     */
    @Override
    public Cell playerMove() {
        Cell result = getCellForMove();
        this.output.write(String.format("Computer move is X = %d, Y = %d", result.showX(), result.showY()));
        makeMove(result, this.cellValue);
        this.output.write(this.board.printDesc());
        return result;
    }

    /**
     * Loads the value to be used by the player.
     * If the player starts first, the value will be X.
     */
    @Override
    public void loadValue(String cellValue) {
        this.cellValue = cellValue;
    }

    /**
     * The method returns the name of the player.
     */
    @Override
    public String showName() {
        return this.name;
    }
}