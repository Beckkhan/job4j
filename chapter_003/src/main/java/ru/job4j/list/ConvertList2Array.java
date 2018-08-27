package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

public class ConvertList2Array {

    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() / rows + 1;
        int[][] array = new int[rows][cells];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            if (index == list.size()) {
                break;
            }
            for (int j = 0; j < cells; j++) {
                if (index < list.size()) {
                    array[i][j] = list.get(index);
                    index++;
                }
            }
        }
        return array;
    }

    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] mas : list) {
            for (int elem : mas) {
                result.add(elem);
            }
        }
        return result;
    }

}
