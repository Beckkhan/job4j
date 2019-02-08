package ru.job4j.inputoutput.consolechat;

import org.junit.Test;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 08.02.2019
 */
public class ConsoleChatTest {
    @Test
    public void whenUserEndsDialog() {
        String logDirectory = "c:\\projects\\job4j\\junior_input_output\\src\\main\\java\\ru\\job4j\\inputoutput\\consolechat\\log.txt";
        ArrayList<String> logPhrases = new ArrayList<>();
        try (FileInputStream finpstrTest = new FileInputStream(logDirectory);
             InputStreamReader inpstrTest = new InputStreamReader(finpstrTest, StandardCharsets.UTF_8);
             BufferedReader readerTest = new BufferedReader(inpstrTest);) {

            String line;
            while ((line = readerTest.readLine()) != null) {
                logPhrases.add(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        assertTrue(logPhrases.get(logPhrases.size() - 2).contains("end"));
        assertTrue(logPhrases.get(logPhrases.size() - 1).contains("До свидания!"));
    }
}