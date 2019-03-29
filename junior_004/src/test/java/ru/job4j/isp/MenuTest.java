package ru.job4j.isp;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 29.03.2019
 */
public class MenuTest {

    @Test
    public void whenCheckThatAddedItemsIsPresentInMenu() {
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
        assertTrue(menu.getRoots().size() == 1);
        assertThat(menu.getRoots().get(0).getName(), is("Задача 1."));
        assertTrue(menu.getItems().size() == 5);
        assertThat(menu.getItems().get("1.").getName(), is("Задача 1."));
        assertThat(menu.getItems().get("1.1.2.").getName(), is("Задача 1.1.2."));
    }
}