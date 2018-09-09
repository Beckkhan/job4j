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
        return j < value[i].length;
    }

    @Override
    public Integer next() {
        int result;
        try {
            result = value[i][j++];
            if (!(hasNext()) && i < value.length - 1) {
                i++;
                j = 0;
            }
        } catch (ArrayIndexOutOfBoundsException aob) {
            throw new NoSuchElementException();
        }
        return result;
    }
}
