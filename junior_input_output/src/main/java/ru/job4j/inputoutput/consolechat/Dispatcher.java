package ru.job4j.inputoutput.consolechat;

import java.io.*;
import java.util.*;
import java.util.function.Function;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * Применение Dispatch Pattern для избежания ситуации с Multiple if statements.
 * @version 1.0
 * @since 16.02.2019
 */
public class Dispatcher {
    private Map<String, Function<String, Boolean>> dispatch = new HashMap<>();
    BufferedWriter br;
    private String phrasesDirectory;
    private ArrayList<String> phrases = new ArrayList<>();
    private Input input;

    public Dispatcher(BufferedWriter br, String phrasesDirectory, Input input) {
        this.br = br;
        this.phrasesDirectory = phrasesDirectory;
        this.setPhraseList();
        this.input = input;
    }

    /**
     * В данном методе загружаем фразы в наш лист phrases.
     */
    public void setPhraseList() {
        try (FileInputStream fins = new FileInputStream(this.phrasesDirectory);
             InputStreamReader inputStreamReader = new InputStreamReader(fins);
             BufferedReader reader = new BufferedReader(inputStreamReader);) {

            String line;
            while ((line = reader.readLine()) != null) {
                phrases.add(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * В этом методе используя Функциональный интерфейс Function<T,R>
     * генерируем булево значение false в ответ на ввод пользователем end.
     */
    public Function<String, Boolean> isEnd() {
        return end -> {
            String goodBye = "До свидания!";
            try {
                br.write(new Date() + " Bot: " + goodBye);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(goodBye);
            return false;
        };
    }

    /**
     * В этом методе используя Функциональный интерфейс Function<T,R>
     * генерируем булево значение false в ответ на ввод пользователем stop.
     */
    public Function<String, Boolean> isStop() {
        return stop -> {
            boolean exit = true;
            while (exit) {
                String userString = input.ask("");
                try {
                    this.br.write(new Date() + " User: " + userString + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                exit = userString.equals("go on") ? this.checkUserPhrase("go on") : true;
            }
            return true;
        };
    }

    /**
     * В этом методе используя Функциональный интерфейс Function<T,R>
     * генерируем булево значение false в ответ на ввод пользователем go on.
     */
    public Function<String, Boolean> isContinue() {
        return go_on -> {
            String phrase = this.phrases.get((int) (Math.random() * this.phrases.size()));
            System.out.println(phrase);
            try {
                this.br.write(new Date() + " Bot: " + phrase + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        };
    }

    /**
     * В этом методе проверяем ввод пользователя.
     * @param userText фраза пользователя
     * @return boolea
     */
    public boolean checkUserPhrase(final String userText) {
        boolean result = true;
        if (this.dispatch.keySet().contains(userText)) {
            result = this.dispatch.get(userText).apply(userText);
        } else {
            String phrase = this.phrases.get((int) (Math.random() * this.phrases.size()));
            System.out.println(phrase);
            try {
                this.br.write(new Date() + " Bot: " + phrase + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Load handlers for destinations.
     * @param type String type
     * @param handle Handler
     */
    public void setDispatch(String type, Function<String, Boolean> handle) {
        this.dispatch.put(type, handle);
    }

    /**
     * Init's dispatch.
     * @return current object.
     */
    public Dispatcher init() {
        this.setDispatch("end", this.isEnd());
        this.setDispatch("stop", this.isStop());
        this.setDispatch("go on", this.isContinue());
        return this;
    }
}