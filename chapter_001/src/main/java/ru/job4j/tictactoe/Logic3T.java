package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        boolean result = true;
        for (int i = 0; i < this.table.length; i++) {
            result = true;
            for (int j = 0; j < this.table.length - 1; j++) {
                if (!(this.table[i][j].hasMarkX() && this.table[i][j + 1].hasMarkX())) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    public boolean isWinnerO() {
        return false;
    }

    public boolean hasGap() {
        return true;
    }
}