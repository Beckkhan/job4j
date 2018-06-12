package ru.job4j.array;

public class MatrixCheck {
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 0; i < data.length-1; i++) {
            for (int j = 0; j < data[i].length-1; j++) {
                if ((i + j) % 2 == 0 && data[i][j] != data[i+1][j+1] && data[i][j] != data[i+1][data[i].length - j - 2]) {
					result = false;
					break;
				}
            }
        }
        return result;
    }
}
// data[i][j] != data[i+1][j+1]
//  || data[i][j] != data[i+1][data[i].length - j - 2]
