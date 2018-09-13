package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {

    private int[][] value;

    private int i = 0;
    private int j = 0;

    public MatrixIterator(int[][] value) {
        this.value = value;
    }

    @Override
    public boolean hasNext() {
        return i < value.length;
    }

    @Override
    public Integer next() {
        if (!(hasNext())) {
            throw new NoSuchElementException();
        }
        int result;
        result = value[i][j++];
        if (j == value[i].length) {
                i++;
                j = 0;
        }
        return result;
    }
}
