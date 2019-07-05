package ru.job4j.additional;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 05.07.2019
 */
public class Switcher {

    private StringBuilder sb;

    public String showSb() {
        return sb.toString();
    }

    public Switcher() {
        this.sb = new StringBuilder();
    }

    public void add(int i) {
        for (int j = 0; j < 10; j++) {
            this.sb.append(i);
        }
    }
}