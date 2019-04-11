package ru.job4j.controltask;

import ru.job4j.controltask.io.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 11.04.2019
 */
public class Game {

    private Board board;
    private PlayingSide human;
    private PlayingSide computer;
    private Output output;

    /**
     * Constructor for the Game.
     * As parameters takes the playing field Board, Computer player, Human player and the output object.
     * @param board
     * @param human
     * @param computer
     * @param output
     */
    public Game(Board board, PlayingSide human, PlayingSide computer, Output output) {
        this.board = board;
        this.human = human;
        this.computer = computer;
        this.output = output;
    }

    /**
     * Method to start the game.
     * As parameters takes a boolean value that determines which player starts the game first.
     * @param computerStart
     */
    public void load(boolean computerStart) {
        if (computerStart) {
            this.computer.loadValue("X");
            this.human.loadValue("O");
            this.computer.playerMove();
        } else {
            this.human.loadValue("X");
            this.computer.loadValue("O");
        }
        while (!this.board.filled()) {
            Cell humanMove = this.human.playerMove();
            if (this.board.identical(humanMove)) {
                this.output.write(String.format("%s is winner", human.showName()));
                break;
            }
            Cell computerMove = this.computer.playerMove();
            if (this.board.identical(computerMove)) {
                this.output.write(String.format("%s is winner", computer.showName()));
                break;
            }
        }
    }
}