package ru.job4j.chess;

import ru.job4j.chess.exception.*;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 5.0
 * @since 29.01.2019
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean rst = false;
        int index = this.findBy(source);
        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);
            checkWay(steps);
            if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        }
        return rst;
    }
    public void clean() {
        Arrays.stream(this.figures).forEach(figure -> figure = null);
        this.index = 0;
        /*for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;*/
    }
    private int findBy(Cell cell) throws FigureNotFoundException {
        int result = IntStream.range(0, this.figures.length)
                .filter(index -> this.figures[index] != null && this.figures[index].position().equals(cell))
                .findFirst()
                .orElse(-1);
        if (result == -1) {
            throw new FigureNotFoundException("Figure not found");
        }
        return result;
        /*int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (!(this.figures[index] != null && this.figures[index].position().equals(cell))) {
                throw new FigureNotFoundException("Figure not found");
            }
            rst = index;
            break;
        }
        return rst;*/
    }
    /**
     * Метод, проверяющий наличие свободного пути для движения фигуры.
     * @param steps Массив шагов.
     * @throws OccupiedWayException Если на пути движения есть фигура.
     */
    private void checkWay(Cell[] steps) throws OccupiedWayException {
        for (Cell step : steps) {
            try {
                if (this.findBy(step) != -1) {
                    throw new OccupiedWayException("There is a figure on the way");
                }
            } catch (FigureNotFoundException exc) {
            }
        }
    }
     /*private void checkWay(Cell[] steps, Figure[] figures) throws OccupiedWayException {
         boolean result = true;
         for (int i = 0; i < steps.length; i++) {
             for (int j = 0; j < figures.length; j++) {
                 if (figures[j] != null && steps[i].equals(figures[j].position())) {
                     result = false;
                     break;
                 }
             }
         }
         if (!result) {
             throw new OccupiedWayException("There is a figure on the way");
         }
     }*/
}