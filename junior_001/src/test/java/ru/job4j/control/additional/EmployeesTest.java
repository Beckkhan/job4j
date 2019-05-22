package ru.job4j.control.additional;

import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 23.05.2019
 */
public class EmployeesTest {
    @Test
    public void whenWeHaveNotBinaryStructureAndManyEmployees() {
        Employees e = new Employees();
        int[] route = {3, 4, 2, 3, 1, 3, 5, 6, 3, 5, 7};
        TreeMap<Integer, ArrayList> expect = new TreeMap<>();
        expect.put(1, new ArrayList(Arrays.asList(0)));
        expect.put(2, new ArrayList(Arrays.asList(0)));
        expect.put(3, new ArrayList(Arrays.asList(1, 4, 5)));
        expect.put(4, new ArrayList(Arrays.asList(2)));
        expect.put(5, new ArrayList(Arrays.asList(6, 7)));
        expect.put(6, new ArrayList(Arrays.asList(0)));
        expect.put(7, new ArrayList(Arrays.asList(0)));
        assertTrue(e.createStructure(route).equals(expect));
    }
    @Test
    public void whenWeHaveBinaryStructureAndNotManyEmployees() {
        Employees e = new Employees();
        int[] route = {3, 4, 2, 3, 1};
        TreeMap<Integer, ArrayList> expect = new TreeMap<>();
        expect.put(1, new ArrayList(Arrays.asList(0)));
        expect.put(2, new ArrayList(Arrays.asList(0)));
        expect.put(3, new ArrayList(Arrays.asList(1, 4)));
        expect.put(4, new ArrayList(Arrays.asList(2)));
        assertTrue(e.createStructure(route).equals(expect));
    }
}