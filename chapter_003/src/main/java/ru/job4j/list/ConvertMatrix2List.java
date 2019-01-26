package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 2.0
 * @since 26.08.2018
 */
public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> result = new ArrayList<>();
        Arrays.stream(array).map(arraystream -> Arrays.stream(arraystream))
                .forEach(stream -> stream.forEach(value -> result.add(value)));
        return result;
        /*List<Integer> list = new ArrayList<>();
        for (int[] row : array) {
            for (int cell : row) {
                list.add(cell);
            }
        }
        return list;*/
    }
}