package ru.job4j.inputoutput.consolechat;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 16.02.2019
 */
public class PreparedInput implements Input {
    private String[] userInputSimulate;
    private int position = 0;

    public PreparedInput(final String[] userInputSimulate) {
        this.userInputSimulate = userInputSimulate;
    }

    @Override
    public String ask(String question) {
        String result = "";
        if (position < userInputSimulate.length) {
            result = userInputSimulate[this.position++];
        }
        return result;
    }
}