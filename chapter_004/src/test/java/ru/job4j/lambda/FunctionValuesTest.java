package ru.job4j.lambda;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Класс FunctionValues с методами для подсчета функций в диапазоне
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2
 * @since 23.01.2019
 * */
public class FunctionValuesTest {

    FunctionValues functionValues = new FunctionValues();

    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = functionValues.diapason(5, 8, i -> 2 * i + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D, 17D);
        assertThat(result, is(expected));
    }
    @Test
    public void whenCheckQuadraticFunc() {
        List<Double> result = functionValues.diapason(1, 3, x -> 2 * x * x + 2 * x - 1);
        List<Double> expect = new ArrayList<>(Arrays.asList(3d, 11d, 23d));
        assertThat(result, is(expect));
    }
    @Test
    public void whenCheckLogarithmicFunc() {
        List<Double> result = functionValues.diapason(1, 3, x -> Math.exp(x) / Math.exp(3));
        List<Double> expect = new ArrayList<>(Arrays.asList(Math.exp(1) / Math.exp(3), Math.exp(2) / Math.exp(3), Math.exp(3) / Math.exp(3)));
        result.forEach(System.out::println);
        assertThat(result, is(expect));
    }
    /*@Test
    public void whenCalculateLinearFunc() {
        FunctionValues functionValues = new FunctionValues();
        List<Double> result = functionValues.linear(1, 3, 2, 1); // x*k + b (х в диапазоне 1 - 3)
        result.forEach(System.out::println);
        List<Double> expect = new ArrayList<>(Arrays.asList(3d, 5d, 7d));
        assertThat(result, is(expect));
    }
    @Test
    public void whenCalculateQuadraticFunc() {
        FunctionValues functionValues = new FunctionValues();
        List<Double> result = functionValues.quadratic(1, 3, 2, 2, -1); // a*x*x + b*x + c (х в диапазоне 1 - 3)
        result.forEach(System.out::println);
        List<Double> expect = new ArrayList<>(Arrays.asList(3d, 11d, 23d));
        assertThat(result, is(expect));
    }
    @Test
    public void whenCalculateLogarithmicFunc() {
        FunctionValues functionValues = new FunctionValues();
        List<Double> result = functionValues.logarithmic(1, 3, 10);
        result.forEach(System.out::println);
        List<Double> expect = new ArrayList<>(Arrays.asList(Math.exp(1) / Math.exp(10), Math.exp(2) / Math.exp(10), Math.exp(3) / Math.exp(10)));
        assertThat(result, is(expect));
    }*/
}