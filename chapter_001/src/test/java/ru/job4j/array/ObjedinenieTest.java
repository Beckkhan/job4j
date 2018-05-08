package ru.job4j.array;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ObjedinenieTest {
    @Test
    public void objedinyaemTest() {
        Objedinenie newarray = new Objedinenie();
        int[] first = {1, 2, 5, 8, 9};
		int[] second = {2, 6, 8, 10};
        int[] result = newarray.objedinyaem(first, second);
        int[] expect = new int[] {1, 2, 2, 5, 6, 8, 8, 9, 10};
        System.out.println("result is: " + Arrays.toString(result));
		System.out.println("expect is: " + Arrays.toString(expect));
		assertThat(result, is(expect));
    }
}