package ru.job4j.inputoutput.consolechat;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 4.0
 * @since 11.02.2019
 */
public class ConsoleChat {

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat();
        //System.out.println(consoleChat.getPhrase());
        consoleChat.startChat();
    }

    public void startChat() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Здравствуйте! Чат запущен.\n");
        stringBuilder.append("Вы можете вводить любой текст.\n");
        stringBuilder.append("Для паузы наберите stop. Для возобновления диалога наберите go on. Для выхода - end.\n");
        stringBuilder.append("Начнем диалог.\n");
        System.out.println(stringBuilder);
        String stringLog = System.getProperty("java.io.tmpdir") + File.separator + "consolechat\\log.txt";
        try (FileWriter fr = new FileWriter(new File(stringLog));
                BufferedWriter br = new BufferedWriter(fr);
             Scanner scanner = new Scanner(System.in)) {
            boolean userWantsCommunicate = true;
            String userText;
            String botAnswer;
            br.write(new Date() + " Bot: " + "Чат запущен!" + System.lineSeparator());
            boolean getBackDialogue = true;
            while (userWantsCommunicate) {
                userText = scanner.nextLine();
                br.write(new Date() + " User: " + userText + System.lineSeparator());
                if (userText.contains("end")) {
                    getBackDialogue = false;
                    userWantsCommunicate = false;
                    String goodBye = "До свидания!";
                    br.write(new Date() + " Bot: " + goodBye);
                    System.out.println(goodBye);
                    break;
                }
                if (userText.contains("stop")) {
                    getBackDialogue = false;
                }
                if (getBackDialogue) {
                    botAnswer = this.getPhrase();
                    System.out.println(botAnswer);
                    br.write(new Date() + " Bot: " + botAnswer + System.lineSeparator());
                }
                if (userText.contains("go on")) {
                    getBackDialogue = true;
                    botAnswer = this.getPhrase();
                    System.out.println(botAnswer);
                    br.write(new Date() + " Bot: " + botAnswer + System.lineSeparator());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPhrase() {
        String phrasesDirectory = "c:\\projects\\job4j\\junior_input_output\\src\\main\\java\\ru\\job4j\\inputoutput\\consolechat\\phrases.txt";
        ArrayList<String> phrases = new ArrayList<>();
        try (FileInputStream fins = new FileInputStream(phrasesDirectory);
             InputStreamReader inputStreamReader = new InputStreamReader(fins);
             BufferedReader reader = new BufferedReader(inputStreamReader);) {

            String line;
            while ((line = reader.readLine()) != null) {
                phrases.add(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        String phrase = phrases.get((int) (Math.random() * phrases.size()));
        return phrase;
    }
}