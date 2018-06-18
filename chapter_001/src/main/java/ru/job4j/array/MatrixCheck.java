package ru.job4j.array;

public class MatrixCheck {
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if ((i + j) % 2 == 0 && data[i][j] != data[i + 1][i + 1]
                                     && data[i][j] != data[i + 1][data[i + 1].length - i - 2]) {
					result = false;
					break;
				}
            }
        }
        return result;
    }
}