package ru.job4j.inputoutput.searchprogram;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 26.02.2019
 */
public class ValidatorTest {
    private String logFileDir = System.getProperty("java.io.tmpdir") + "Search\\";
    private Map<String, String> parameters;
    private Validator validator;

    @Test
    public void whenCheckValidatorWithBadParametersThenFalse() {
        parameters = new HashMap<>();
        parameters.put("-d", null);
        parameters.put("-n", "*.jpg");
        parameters.put("-m", null);
        parameters.put("-o", logFileDir + "log.txt");
        validator = new Validator(parameters);
        assertFalse(validator.validate());
    }

    @Test
    public void whenCheckValidatorWithGoodParametersThenTrue() {
        parameters = new HashMap<>();
        parameters.put("-d", logFileDir);
        parameters.put("-n", "*.jpg");
        parameters.put("-m", null);
        parameters.put("-o", logFileDir + "log.txt");
        validator = new Validator(parameters);
        assertTrue(validator.validate());
    }
}
