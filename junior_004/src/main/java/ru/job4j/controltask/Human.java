package ru.job4j.controltask;

import ru.job4j.controltask.io.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 10.04.2019
 */
public class Human implements PlayingSide {

    private Board board;
    private Input input;
    private Output output;
    private String name = "Human";
    private String cellValue;

    /**
     * Constructor for the player, which is a Human.
     * As parameters takes the playing field Board, input and the output object.
     * @param board
     * @param input
     * @param output
     */
    public Human(Board board, Input input, Output output) {
        this.board = board;
        this.input = input;
        this.output = output;
    }

    /**
     * Redefining the method of making a move, which includes auxiliary method makeMove().
     */
    @Override
    public Cell playerMove() {
        boolean valid = false;
        String humanX = "";
        String humanY = "";
        while (!valid) {
            this.output.write(String.format("Please enter X coordinate >=0 and <%d: ", this.board.size()));
            humanX = input.read();
            this.output.write(String.format("Please enter Y coordinate >=0 and <%d: ", this.board.size()));
            humanY = input.read();
            try {
                if (Integer.parseInt(humanX) >= this.board.size() || Integer.parseInt(humanY) >= this.board.size()) {
                    valid = false;
                    throw new Exception("Invalid coordinates: Out of board size.");
                }
                this.makeMove(this.board.showCell(Integer.parseInt(humanX), Integer.parseInt(humanY)), this.cellValue);
                valid = true;
            } catch (NumberFormatException nfe) {
                this.output.write(humanX + ":" + humanY + "Coordinates should be positive numbers less then board size.");
                nfe.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.output.write(String.format("Human move is X = %s, Y = %s", humanX, humanY));
        this.output.write(this.board.printDesc());
        return this.board.showCell(Integer.parseInt(humanX), Integer.parseInt(humanY));
    }

    /**
     * A method for making a move by a Human.
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