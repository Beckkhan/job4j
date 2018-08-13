package ru.job4j.chess.figures.black;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.exception.*;

/**
 * @author Khan Vyacheslav (beckkhan@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {

        int deltaX = source.getX() - dest.getX();
        int deltaY = source.getY() - dest.getY();
        int size = Math.abs(deltaX);
        if (Math.abs(deltaX) != Math.abs(deltaY)) {
            throw new ImpossibleMoveException("It`s impossible to move this way");
        }

        Cell[] steps = new Cell[size];
        for (int i = 0; i < size; i++) {
            steps[i] = Cell.setNewCell((source.getX() + (deltaX < 0 ? 1 : -1) * (i + 1)),
                    (source.getY() + (deltaY < 0 ? 1 : -1) * (i + 1)));
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
