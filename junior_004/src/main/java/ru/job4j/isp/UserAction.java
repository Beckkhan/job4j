package ru.job4j.isp;

import java.util.Scanner;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 29.03.2019
 */
public class UserAction {

    private Scanner scanner;
    private Menu menu;

    public UserAction(Scanner scanner, Menu menu) {
        this.scanner = scanner;
        this.menu = menu;
    }

    public void run() {
        String input;
        do {
            menu.showMenu();
            input = this.scanner.nextLine();
            if (this.menu.getItems().containsKey(input)) {
                this.menu.getItems().get(input).itemAction();
            } else if (!input.equalsIgnoreCase("exit")) {
                System.out.println("Пункт не существует");
            }
        } while (!input.equalsIgnoreCase("exit"));
        System.out.println("Работа приложения завершена");
    }

    public static void main(String[] args) {
        Item first = new Item(null, "Задача 1.");
        Item second = new Item(first, "Задача 1.1.");
        Item third = new Item(second, "Задача 1.1.1.");
        Item fourth = new Item(second, "Задача 1.1.2.");
        Item fifth = new Item(first, "Задача 1.2.");
        Menu menu = new Menu();
        menu.add(first);
        menu.add(second);
        menu.add(third);
        menu.add(fourth);
        menu.add(fifth);
        Scanner scanner = new Scanner(System.in);
        UserAction userAction = new UserAction(scanner, menu);
        userAction.run();
    }
}