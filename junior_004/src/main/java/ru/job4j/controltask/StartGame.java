package ru.job4j.controltask;

import ru.job4j.controltask.io.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 11.04.2019
 */
public class StartGame {

    public static void main(String[] args) throws Exception {
        Input input = new ConsoleInput();
        Output output = new ConsoleOutput();
        output.write("Let`s start the game.");

        Setup setup = new Setup(input, output);

        int size;
        size = setup.clarifyBoardSize();

        Cell[][] cellset = new Cell[size][size];
        for (int i = 0; i < cellset.length; i++) {
            for (int j = 0; j < cellset.length; j++) {
                cellset[i][j] = new Cell(i, j);
                cellset[i][j].editValue("");
            }
        }

        Board board = new Board(cellset);
        PlayingSide human = new Human(board, input, output);
        PlayingSide computer = new Computer(board, output);
        Game game = new Game(board, human, computer, output);
        boolean userStart = setup.clarifyBeginner();
        game.load(userStart);
    }
}