package ru.job4j.controltask;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 05.04.2019
 */
public class Board {

    private Cell[][] cellset;

    /**
     * Constructor for the playing field Board.
     * @param cellset Takes a set of cells as a two-dimensional array as a parameter.
     */
    public Board(Cell[][] cellset) {
        this.cellset = cellset;
    }

    /**
     * Checking the cell for emptiness.
     * @param x X coordinate of Cell.
     * @param y Y coordinate of Cell
     * @return true if Cell is empty.
     */
    public boolean empty(int x, int y) {
        return this.cellset[x][y].showValue().equals("");
    }

    /**
     * Returns the size of the playing field.
     */
    public int size() {
        return this.cellset.length;
    }

    /**
     * Returns the Cell at the specified coordinates.
     */
    public Cell showCell(int x, int y) {
        return this.cellset[x][y];
    }

    /**
     * Change the value that is contained in the Cell.
     */
    public void editCell(int x, int y, String value) {
        this.cellset[x][y].editValue(value);
    }

    /**
     * Check for matching values in Cells horizontally and vertically.
     * @param cell
     * @return true if there is a match.
     */
    public boolean checkLine(Cell cell) {
        boolean result = false;
        int matchX = 0;
        int matchY = 0;
        int x = cell.showX();
        int y = cell.showY();
        for (int i = 0; i < this.cellset.length; i++) {
            if (this.cellset[x][i].showValue().equals(cell.showValue())) {
                matchY++;
            }
            if (this.cellset[i][y].showValue().equals(cell.showValue())) {
                matchX++;
            }
        }
        if (matchX == cellset.length || matchY == cellset.length) {
            result = true;
        }
        return result;
    }

    /**
     * Check for matching values in Cells on the diagonal.
     * @param cell
     * @return true if there is a match.
     */
    public boolean checkDiagonal(Cell cell) {
        boolean result = false;
        int matchLeft = 0;
        int matchRight = 0;
        int x = cell.showX();
        int y = cell.showY();
        if (x == y || (x + y) == (this.cellset.length - 1)) {
            for (int i = 0; i < this.cellset.length; i++) {
                if (this.cellset[i][i].showValue().equals(cell.showValue())) {
                    matchLeft++;
                }
                if (this.cellset[i][this.cellset.length - i - 1].showValue().equals(cell.showValue())) {
                    matchRight++;
                }
            }
        }
        if (matchLeft == cellset.length || matchRight == cellset.length) {
            result = true;
        }
        return result;
    }

    /**
     * The method checks if the values in the Cells match in all possible directions.
     * @param cell
     * @return true if there is a match.
     */
    public boolean identical(Cell cell) {
        boolean result = false;
        if (checkLine(cell) || checkDiagonal(cell)) {
            result = true;
        }
        return result;
    }

    /**
     * The method checks free Cells on the playing field.
     * @return false if the playing field is not already filled in.
     */
    public boolean filled() {
        for (Cell[] cell : this.cellset) {
            for (int i = 0; i < this.cellset.length; i++) {
                if (cell[i].showValue().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method for displaying the playing field as a string.
     * @return String that can be printed and thus we get the playing field at the moment in the pseudographic.
     */
    public String printDesc() {
        StringBuilder sb = new StringBuilder();
        int k = 0;
        sb.append("y/x");
        for (int i = 0; i < this.cellset.length; i++) {
            sb.append("  " + i + ":");
        }
        sb.append(System.getProperty("line.separator"));
        sb.append(printDivider(this.cellset.length));
        for (int i = 0; i < this.cellset.length; i++) {
            sb.append(k + ": | ");
            for (int j = 0; j < this.cellset.length; j++) {
                sb.append(this.cellset[j][i]);
                sb.append(" | ");
            }
            sb.append(System.getProperty("line.separator"));
            sb.append(printDivider(this.cellset.length));
            k++;
        }
        return sb.toString();
    }

    /**
     * This is an auxiliary method for printing a horizontal cell separator.
     * @return String with horizontal dashes.
     */
    private String printDivider(int length) {
        StringBuilder sb = new StringBuilder();
        sb.append("    ");
        for (int i = 0; i < length - 1; i++) {
            sb.append("----");
        }
        sb.append("---");
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }
}