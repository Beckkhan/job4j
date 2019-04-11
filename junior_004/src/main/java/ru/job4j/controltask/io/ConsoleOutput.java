package ru.job4j.controltask.io;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 05.04.2019
 */
public class ConsoleOutput implements Output {

    @Override
    public void write(String out) {
        System.out.println(out);
    }
}