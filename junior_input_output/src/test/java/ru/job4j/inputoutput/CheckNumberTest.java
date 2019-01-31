package ru.job4j.inputoutput;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 01.02.2019
 */
public class CheckNumberTest {
    @Test
    public void whenCheck10ThenTrue() {
        CheckNumber checkNumber = new CheckNumber();
        ByteArrayInputStream bais = new ByteArrayInputStream("10".getBytes());
        assertThat(checkNumber.isNumber(bais), is(true));
    }
    @Test
    public void whenCheck9ThenFalse() {
        CheckNumber checkNumber = new CheckNumber();
        ByteArrayInputStream bais = new ByteArrayInputStream("9".getBytes());
        assertThat(checkNumber.isNumber(bais), is(false));
    }
    @Test
    public void whenCheckNotNumber() {
        CheckNumber checkNumber = new CheckNumber();
        ByteArrayInputStream bais = new ByteArrayInputStream("Number".getBytes());
        assertThat(checkNumber.isNumber(bais), is(false));
    }
}