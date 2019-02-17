package ru.job4j.inputoutput.consolechat;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 9.0
 * @since 17.02.2019
 */
public class ConsoleChatTest {

    @Test
    public void whenCheckConsoleChat() {
        String stringLog = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "consolechat\\log.txt";
        String[] userInputSimulate = {"privet", "stop", "so what", "go on", "end"};
        PreparedInput preparedInput = new PreparedInput(userInputSimulate);
        ConsoleChat consoleChat = new ConsoleChat(preparedInput, stringLog);
        consoleChat.startChat();
        ArrayList<String> logPhrases = new ArrayList<>();
        try (FileInputStream finpstrTest = new FileInputStream(stringLog);
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