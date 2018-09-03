package ru.job4j.tictactoe;

import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }


    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isWinnerX() {
        return this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 0)
                || this.fillBy(Figure3T::hasMarkX, 0, 1, 1, 0)
                || this.fillBy(Figure3T::hasMarkX, 0, 2, 1, 0)
                || this.fillBy(Figure3T::hasMarkX, 0, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkX, 1, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkX, 2, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 1)
                || this.fillBy(Figure3T::hasMarkX, this.table.length - 1, 0, -1, 1);
    }

    public boolean isWinnerO() {
        return this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 0)
                || this.fillBy(Figure3T::hasMarkO, 0, 1, 1, 0)
                || this.fillBy(Figure3T::hasMarkO, 0, 2, 1, 0)
                || this.fillBy(Figure3T::hasMarkO, 0, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkO, 1, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkO, 2, 0, 0, 1)
                || this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 1)
                || this.fillBy(Figure3T::hasMarkO, this.table.length - 1, 0, -1, 1);
    }

    public boolean hasGap() {
        boolean gapIsFree = false;
        for (Figure3T[] row : table) {
            for (Figure3T cell : row) {
                if ((!cell.hasMarkX()) && (!cell.hasMarkO())) {
                    gapIsFree = true;
                    break;
                }
            }
        }
        return gapIsFree;
    }

//    public boolean isWinnerX() {
//        boolean dia = false;
//        boolean row = false;
//        boolean column = false;
//
//        for (int i = 0; i < this.table.length - 1; i++) {
//            for (int j = 0; j < this.table[i].length; j++) {
//                if ((i + j) % 2 == 0 && (this.table[i][j].hasMarkX() && this.table[i + 1][i + 1].hasMarkX())
//                        && (this.table[i][j].hasMarkX()) && this.table[i + 1][this.table[i + 1].length - i - 2].hasMarkX()) {
//                    dia = true;
//                    break;
//                }
//            }
//        }
//        for (int i = 0; i < this.table.length; i++) {
//            for (int j = 0; j < this.table.length - 1; j++) {
//                if ((this.table[i][j].hasMarkX()) && (this.table[i][j + 1].hasMarkX())) {
//                    row = true;
//                    break;
//                }
//            }
//        }
//        for (int i = 0; i < this.table.length - 1; i++) {
//            for (int j = 0; j < this.table.length; j++) {
//                if (this.table[i][j].hasMarkX() && this.table[i + 1][j].hasMarkX()) {
//                    column = true;
//                    break;
//                }
//            }
//        }
//        return dia || row || column;
//    }

//    public boolean isWinnerO() {
//        boolean dia = false;
//        boolean row = false;
//        boolean column = false;
//
//        for (int i = 0; i < this.table.length - 1; i++) {
//            for (int j = 0; j < this.table[i].length; j++) {
//                if ((i + j) % 2 == 0 && (this.table[i][j].hasMarkO() && this.table[i + 1][i + 1].hasMarkO())
//                        && (this.table[i][j].hasMarkO()) && this.table[i + 1][this.table[i + 1].length - i - 2].hasMarkO()) {
//                    dia = true;
//                    break;
//                }
//            }
//        }
//        for (int i = 0; i < this.table.length; i++) {
//            for (int j = 0; j < this.table.length - 1; j++) {
//                if ((this.table[i][j].hasMarkO()) && (this.table[i][j + 1].hasMarkO())) {
//                    row = true;
//                    break;
//                }
//            }
//        }
//        for (int i = 0; i < this.table.length - 1; i++) {
//            for (int j = 0; j < this.table.length; j++) {
//                if (this.table[i][j].hasMarkO() && this.table[i + 1][j].hasMarkO()) {
//                    column = true;
//                    break;
//                }
//            }
//        }
//        return dia || row || column;
//    }
}