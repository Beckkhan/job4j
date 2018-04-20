package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Максимум из двух чисел.
 */
public class MaxTest {
    @Test
    public void whenFirstLessSecond() {
    Max maxim = new Max();
    int maximum = maxim.max(7, 8);
    assertThat(maximum, is(8));
    }
}