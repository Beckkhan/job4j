package ru.job4j.controltask;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 05.04.2019
 */
public class Cell {

    private int x;
    private int y;
    private String value;

    /**
     * Creating a constructor for Cell.
     * @param x X coordinate of Cell.
     * @param y Y coordinates of Cell.
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The method returns the value of the X coordinate of Cell.
     */
    public int showX() {
        return this.x;
    }

    /**
     * The method returns the value of the Y coordinate of Cell.
     */
    public int showY() {
        return this.y;
    }

    /**
     * The method returns the value of Cell.
     * @return String value
     */
    public String showValue() {
        return value;
    }

    /**
     * The setter for the value of Cell.
     * @param value
     */
    public void editValue(String value) {
        this.value = value;
    }

    /**
     * Overriding the method to get the value of Cell.
     * @return String value
     */
    @Override
    public String toString() {
        return this.value;
    }
}