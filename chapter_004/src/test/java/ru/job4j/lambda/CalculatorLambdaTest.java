package ru.job4j.lambda;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CalculatorLambdaTest {
    @Test
    public void whenAdd1Until3() {
        CalculatorLambda calc = new CalculatorLambda();
        List<Double> buffer = new ArrayList<>();
        calc.multiple(
                0, 3, 1,
                MathUtil::add, //static call
                buffer::add // non-static call
        );
        assertThat(buffer, is(Arrays.asList(1D, 2D, 3D)));
    }
    /*@Test
    public void whenAdd1Until3() {
        CalculatorLambda calc = new CalculatorLambda();
        List<Double> buffer = new ArrayList<>();
        calc.multiple(
                0, 3, 1,
                (value, index) -> (double) value + index,
                result -> buffer.add(result)
        );
        assertThat(buffer, is(Arrays.asList(1D, 2D, 3D)));
    }*/
}