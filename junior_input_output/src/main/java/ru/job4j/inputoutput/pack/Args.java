package ru.job4j.inputoutput.pack;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 05.02.2019
 */
public class Args {
    private String directory;
    private String exclude;
    private String output;

    public Args(String[] args) {
        this.directory = args[0];
        this.exclude = args[1];
        this.output = args[2];
    }

    public String output() {
        return output;
    }

    public String exclude() {
        return exclude;
    }

    public String directory() {
        return directory;
    }
}