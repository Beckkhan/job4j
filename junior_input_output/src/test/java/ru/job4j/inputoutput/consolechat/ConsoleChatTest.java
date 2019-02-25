package ru.job4j.inputoutput.consolechat;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 10.0
 * @since 26.02.2019
 */
public class ConsoleChatTest {
    @Test
    public void whenCheckConsoleChat() {
        String logFileName = null;
        try {
            String stringLog = System.getProperty("java.io.tmpdir") + "consolechat\\";
            File main = new File(stringLog);
            if (!main.exists()) {
                main.mkdirs();
            }
            logFileName = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "consolechat\\log.txt";
            File log = new File(logFileName);
            if (!log.exists()) {
                log.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] userInputSimulate = {"privet", "stop", "so what", "go on", "end"};
        PreparedInput preparedInput = new PreparedInput(userInputSimulate);
        ConsoleChat consoleChat = new ConsoleChat(preparedInput, logFileName);
        consoleChat.startChat();
        ArrayList<String> logPhrases = new ArrayList<>();
        try (FileInputStream finpstrTest = new FileInputStream(logFileName);
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
    }
}