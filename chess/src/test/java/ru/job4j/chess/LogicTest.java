package ru.job4j.chess;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import ru.job4j.chess.exception.FigureNotFoundException;
import ru.job4j.chess.exception.ImpossibleMoveException;
import ru.job4j.chess.exception.OccupiedWayException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.black.*;
import ru.job4j.chess.figures.white.*;
/**
 * @author Khan Vyacheslav (beckkhan@mail.ru)
 * @version $Id$
 * @since 12/08/2018
 */

public class LogicTest {
    @Test
    public void whenPawnBlackCanMove()  {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.F7));
        try {
            assertThat(logic.move(Cell.F7, Cell.F6), is(true));
        } catch (ImpossibleMoveException e) {
            e.printStackTrace();
        } catch (OccupiedWayException e) {
            e.printStackTrace();
        } catch (FigureNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void whenPawnWhiteCanMove()  {
        Logic logic = new Logic();
        logic.add(new PawnWhite(Cell.G2));
        try {
            assertThat(logic.move(Cell.G2, Cell.G3), is(true));
        } catch (ImpossibleMoveException e) {
            e.printStackTrace();
        } catch (OccupiedWayException e) {
            e.printStackTrace();
        } catch (FigureNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void whenPawnBlackCanNotMove() {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.F7));
        try {
            assertThat(logic.move(Cell.F7, Cell.G6), is(false));
        } catch (ImpossibleMoveException e) {
            e.printStackTrace();
        } catch (OccupiedWayException e) {
            e.printStackTrace();
        } catch (FigureNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void whenNoPawnBlack() {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.H7));
        try {
            assertThat(logic.move(Cell.F7, Cell.F6), is(false));
        } catch (ImpossibleMoveException e) {
            e.printStackTrace();
        } catch (OccupiedWayException e) {
            e.printStackTrace();
        } catch (FigureNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void whenBishopBlackCanMove() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.F2));
        try {
            assertThat(logic.move(Cell.F2, Cell.B6), is(true));
        } catch (ImpossibleMoveException e) {
            e.printStackTrace();
        } catch (OccupiedWayException e) {
            e.printStackTrace();
        } catch (FigureNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void whenBishopBlackCanNotMove() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.F8));
        try {
            assertThat(logic.move(Cell.F8, Cell.B1), is(false));
        } catch (ImpossibleMoveException e) {
            e.printStackTrace();
        } catch (OccupiedWayException e) {
            e.printStackTrace();
        } catch (FigureNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void whenPawnBlackWantToMoveAndFigureNotOnWay() {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.D7));
        logic.add(new BishopBlack(Cell.E6));
        try {
            assertThat(logic.move(Cell.D7, Cell.D6), is(true));
        } catch (ImpossibleMoveException e) {
            e.printStackTrace();
        } catch (OccupiedWayException e) {
            e.printStackTrace();
        } catch (FigureNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void whenPawnBlackWantToMoveButFigureOnWay() {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.D7));
        logic.add(new BishopBlack(Cell.D6));
        try {
            assertThat(logic.move(Cell.D7, Cell.D6), is(false));
        } catch (FigureNotFoundException e) {
            e.printStackTrace();
        } catch (ImpossibleMoveException e) {
            e.printStackTrace();
        } catch (OccupiedWayException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void whenBishopBlackWantToMoveAndFigureNotOnWay() {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.D7));
        logic.add(new BishopBlack(Cell.F8));
        try {
            assertThat(logic.move(Cell.F8, Cell.D6), is(true));
        } catch (ImpossibleMoveException e) {
            e.printStackTrace();
        } catch (OccupiedWayException e) {
            e.printStackTrace();
        } catch (FigureNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void whenBishopBlackWantToMoveButFigureOnWay() {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.E7));
        logic.add(new BishopBlack(Cell.F8));
        try {
            assertThat(logic.move(Cell.F8, Cell.D6), is(false));
        } catch (ImpossibleMoveException e) {
            e.printStackTrace();
        } catch (OccupiedWayException e) {
            e.printStackTrace();
        } catch (FigureNotFoundException e) {
            e.printStackTrace();
        }
    }
}