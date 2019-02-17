package ru.job4j.inputoutput.consolechat;

import java.io.*;
import java.util.*;
import java.util.function.Function;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 9.0
 * @since 16.02.2019
 */
public class ConsoleChat {
    private String stringLog;
    private String phrasesDirectory;
    private Input input;

    public ConsoleChat(Input input, String stringLog) {
        this.stringLog = stringLog;
        this.phrasesDirectory = ConsoleChat.class.getClassLoader().getResource("phrases.txt").getPath();
        this.input = input;
    }

    public static void main(String[] args) {
        Input input = new UserInput();
        String stringLog = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "consolechat\\log.txt";
        ConsoleChat consoleChat = new ConsoleChat(input, stringLog);
        consoleChat.startChat();
    }

    public void startChat() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Здравствуйте! Чат запущен.\n");
        stringBuilder.append("Вы можете вводить любой текст.\n");
        stringBuilder.append("Для паузы наберите stop. Для возобновления диалога наберите go on. Для выхода - end.\n");
        stringBuilder.append("Начнем диалог.\n");
        System.out.println(stringBuilder);
        try (FileWriter fr = new FileWriter(new File(stringLog));
             BufferedWriter br = new BufferedWriter(fr);) {
            Dispatcher dispatcher = new Dispatcher(br, phrasesDirectory, this.input).init();
            boolean userWantsCommunicate = true;
            br.write(new Date() + " Bot: " + "Чат запущен!" + System.lineSeparator());
            while (userWantsCommunicate) {
                String userText = input.ask("");
                br.write(new Date() + " User: " + userText + System.lineSeparator());
                userWantsCommunicate = dispatcher.checkUserPhrase(userText);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}