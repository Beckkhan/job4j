package ru.job4j.chess.figures.white;

import ru.job4j.chess.exception.*;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * @author Khan Vyacheslav (beckkhan@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class KnightWhite implements Figure {
    private final Cell position;

    public KnightWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {

        if (!(Math.abs(dest.getX() - source.getX()) == 2 && Math.abs(dest.getY() - source.getY()) == 1
                || Math.abs(dest.getX() - source.getX()) == 1 && Math.abs(dest.getY() - source.getY()) == 2)) {
            throw new ImpossibleMoveException("It`s impossible to move this way");
        }
        return new Cell[] {dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new KnightWhite(dest);
    }
}
