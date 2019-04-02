package ru.job4j.tdd;

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 02.04.2019
 */
public class SimpleGeneratorTest {

    @Test
    public void whenFirstCheckWithTwoKeys() throws Exception {
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Somebody");
        map.put("subject", "you");
        String expect = "I am a Somebody, Who are you?";
        assertThat(simpleGenerator.generate(template, map), is(expect));
    }

    @Test
    public void whenCheckWithOneKey() throws Exception {
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        String template = "Help, ${sos}, ${sos}, ${sos}!";
        Map<String, String> map = new HashMap<>();
        map.put("sos", "Aaaa");
        String expect = "Help, Aaaa, Aaaa, Aaaa!";
        assertThat(simpleGenerator.generate(template, map), is(expect));
    }

    @Test (expected = Exception.class)
    public void whenCheckForExceptionWhenTooManyKeys() throws Exception {
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Somebody");
        map.put("subject", "you");
        map.put("sos", "Aaaa");
        simpleGenerator.generate(template, map);
    }

    @Test (expected = Exception.class)
    public void whenCheckForExceptionWhenHaveBadKey() throws Exception {
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        String template = "Help, ${sos}, ${sos}, ${sos}!";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Somebody");
        map.put("subject", "you");
        simpleGenerator.generate(template, map);
    }
}