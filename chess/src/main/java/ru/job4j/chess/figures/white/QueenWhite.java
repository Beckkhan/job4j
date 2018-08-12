package ru.job4j.chess.figures.white;

import ru.job4j.chess.exception.*;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * @author Khan Vyacheslav (beckkhan@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class QueenWhite implements Figure {
    private final Cell position;

    public QueenWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {

        if (!((source.getX() - source.getY()) == (dest.getX() - dest.getY())
                || (source.getX() + source.getY()) == (dest.getX() + dest.getY())
                || (source.getX() == dest.getX()) && (source.getY() != dest.getY())
                || ((source.getX() != dest.getX()) && (source.getY() == dest.getY())))) {
            throw new ImpossibleMoveException("It`s impossible to move this way");
        }
        return new Cell[] {dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new QueenWhite(dest);
    }
}
