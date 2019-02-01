package ru.job4j.inputoutput;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 01.02.2019
 */
public class AbuseWordsTest {
    @Test
    public void whenTryToCheck() {
        AbuseWords abuseWords = new AbuseWords();
        String[] abuses = {"when", "words"};
        String string = "when try to catch abuse words";
        ByteArrayInputStream in = new ByteArrayInputStream(string.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String expect = "try to catch abuse";
        abuseWords.dropAbuses(in, out, abuses);
        assertThat(out.toString(), is(expect));
    }
}