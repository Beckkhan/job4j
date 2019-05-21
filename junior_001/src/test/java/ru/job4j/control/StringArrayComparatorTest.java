package ru.job4j.control;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 22.05.2019
 */
public class StringArrayComparatorTest {
    @Test
    public void whenCompareThenTrue() {
        StringArrayComparator comp = new StringArrayComparator();
        String[] a = {"j", "o", "b", "4", "j"};
        String[] b = {"b", "j", "4", "o", "j"};
        assertTrue(comp.compareArr(a, b));
    }
    @Test
    public void whenCompareThenFalse() {
        StringArrayComparator comp = new StringArrayComparator();
        String[] a = {"j", "o", "b", "4", "j"};
        String[] b = {"j", "r", "u", "s", "h"};
        assertFalse(comp.compareArr(a, b));
    }
}