package ru.job4j.controltask;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 11.04.2019
 */
public class BoardTest {

    @Test
    public void whenCheckBoardPrinting() {
        Cell c1 = new Cell(0, 0);
        c1.editValue("0");
        Cell c2 = new Cell(1, 0);
        c2.editValue("X");
        Cell c3 = new Cell(2, 0);
        c3.editValue("X");
        Cell c4 = new Cell(0, 1);
        c4.editValue("X");
        Cell c5 = new Cell(1, 1);
        c5.editValue("X");
        Cell c6 = new Cell(2, 1);
        c6.editValue("0");
        Cell c7 = new Cell(0, 2);
        c7.editValue("X");
        Cell c8 = new Cell(1, 2);
        c8.editValue("X");
        Cell c9 = new Cell(2, 2);
        c9.editValue("0");
        Cell[][] cellset = new Cell[][]{
                {c1, c4, c7},
                {c2, c5, c8},
                {c3, c6, c9},
        };
        Board board = new Board(cellset);
        StringBuilder sb = new StringBuilder();
        sb.append("y/x  0:  1:  2:");
        sb.append(System.getProperty("line.separator"));
        sb.append("    -----------");
        sb.append(System.getProperty("line.separator"));
        sb.append("0: | 0 | X | X | ");
        sb.append(System.getProperty("line.separator"));
        sb.append("    -----------");
        sb.append(System.getProperty("line.separator"));
        sb.append("1: | X | X | 0 | ");
        sb.append(System.getProperty("line.separator"));
        sb.append("    -----------");
        sb.append(System.getProperty("line.separator"));
        sb.append("2: | X | X | 0 | ");
        sb.append(System.getProperty("line.separator"));
        sb.append("    -----------");
        sb.append(System.getProperty("line.separator"));
        assertTrue(board.printDesc().equals(sb.toString()));
    }
}
