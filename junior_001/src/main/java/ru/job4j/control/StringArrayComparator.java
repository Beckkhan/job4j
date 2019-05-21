package ru.job4j.control;

import java.util.*;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 22.05.2019
 */
public class StringArrayComparator {

    /**
     * The method checks the equality of string arrays.
     * The order of elements in arrays does not matter.
     * @return boolean
     */
    public boolean compareArr(String[] a, String[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }
}