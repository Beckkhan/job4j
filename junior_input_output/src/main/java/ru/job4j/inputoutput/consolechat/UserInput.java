package ru.job4j.inputoutput.consolechat;

import java.util.Scanner;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 15.02.2019
 */
public class UserInput implements Input {
    private Scanner scanner = new Scanner(System.in);
    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}