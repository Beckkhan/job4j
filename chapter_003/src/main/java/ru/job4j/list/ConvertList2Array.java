package ru.job4j.list;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 2.0
 * @since 27.08.2018
 */
public class ConvertList2Array {

    public int[][] toArray(List<Integer> list, int rows) {
        Iterator<Integer> iterator = list.iterator();
        int cells = list.size() / rows + (list.size() % rows == 0 ? 0 : 1);
        int[][] array = new int[rows][cells];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cells; j++) {
                if (iterator.hasNext()) {
                    array[i][j] = iterator.next();
                } else {
                    array[i][j] = 0;
                }
            }
        }
        return array;
    }

    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        list.stream().map(arraystream -> Arrays.stream(arraystream))
                .forEach(stream -> stream.forEach(value -> result.add(value)));
        return result;

        /*List<Integer> result = new ArrayList<>();
        for (int[] mas : list) {
            for (int elem : mas) {
                result.add(elem);
            }
        }
        return result;*/
    }
}