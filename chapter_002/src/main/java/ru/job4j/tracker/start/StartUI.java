package ru.job4j.tracker.start;

import ru.job4j.tracker.ITracker;

import java.util.function.Consumer;

/**
 * Класс StartUI с заменой вывода в консоль на Consumer.
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 4.0
 * @since 12.04.2019
 **/
public class StartUI {

    private Input input;
    private final ITracker tracker;
    private final Consumer<String> output;

    public StartUI(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker, output);
        menu.fillActions();

        do {
            menu.show();
            menu.select(input.ask("Select:", menu.getActionsNum()));
        }
        while (!"y".equals(this.input.ask("Exit?(y):")));
    }

    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker(), System.out::println).init();
    }
}