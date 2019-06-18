package ru.job4j.bomberman;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 18.06.2019
 */
public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    /**
     * The value of the horizontal coordinate change.
     */
    public final int deltax;

    /**
     * The amount of change of coordinates in vertical.
     */
    public final int deltay;

    Direction(int deltax, int deltay) {
        this.deltax = deltax;
        this.deltay = deltay;
    }
}