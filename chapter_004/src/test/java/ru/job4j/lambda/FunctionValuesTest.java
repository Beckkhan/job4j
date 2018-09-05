package ru.job4j.lambda;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FunctionValuesTest {

    @Test
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
    }
}
