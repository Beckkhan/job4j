package ru.job4j.chess;

import ru.job4j.chess.exception.*;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * @author Khan Vyacheslav (beckkhan@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        try {
            int index = this.findBy(source);
        } catch (FigureNotFoundException fnfe) {
            System.out.println("Please try another figure");
        }
        if (index != -1) {
            Cell[] steps = null;
            try {
                steps = this.figures[index].way(source, dest);
                checkWay(steps, this.figures);
            } catch (ImpossibleMoveException ime) {
                System.out.println("Please try another move");
            } catch (OccupiedWayException owe) {
                System.out.println("Please try another way");
            }
            if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) throws FigureNotFoundException {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (!(this.figures[index] != null && this.figures[index].position().equals(cell))) {
                throw new FigureNotFoundException("Figure not found");
            }
            rst = index;
            break;
        }
        return rst;
    }

    /**
     * Метод, проверяющий наличие свободного пути для движения фигуры.
     * @param steps Массив шагов.
     * @param figures Массив фигур.
     * @throws OccupiedWayException Если на пути движения есть фигура.
     */
    private void checkWay(Cell[] steps, Figure[] figures) throws OccupiedWayException {
        for (int i = 0; i < steps.length ; i++) {
            for (int j = 0; j < figures.length ; j++) {
                if(figures[j]!=null && steps[i].equals(figures[j].position())){
                    throw new OccupiedWayException("There is a figure on the way");
                }
                break;
            }
        }
    }
}
