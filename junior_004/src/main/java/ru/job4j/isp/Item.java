package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 29.03.2019
 */
public class Item {
    private final String name;
    private Item parent;
    private final List<Item> children = new ArrayList<>();

    public Item(Item parent, final String name) {
        this.parent = parent;
        this.name  = name;
    }

    public void add(Item child) {
        this.children.add(child);
    }

    public void itemAction() {
        System.out.println("Вами выбран пункт меню: " + this.name);
    }

    public String getName() {
        return this.name;
    }

    public List<Item> leaves() {
        return this.children;
    }

    public Item getParent() {
        return parent;
    }

    public int getLevel() {
        int result = 0;
        Item item = this;
        while (item.parent != null) {
            item = item.getParent();
            result++;
        }
        return result;
    }
}