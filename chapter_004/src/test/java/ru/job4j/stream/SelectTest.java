package ru.job4j.stream;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 30.01.2019
 */
public class SelectTest {
    @Test
    public void whenSelectStudents() {
        Select select = new Select();
        Student first = new Student("Sainu", 5);
        Student second = new Student("Dita", 8);
        Student third = new Student("Karu", 10);
        Student fourth = new Student("Mumik", 3);
        Student fifth = new Student("Naru", 4);
        List<Student> students = new ArrayList<>(Arrays.asList(null, first, second, null, third, null, fourth, fifth));
        List<Student> result = select.levelOf(students, 4);
        List<Student> expect = new ArrayList<>(Arrays.asList(third, second, first));
        assertThat(result, is(expect));
    };
}