package ru.job4j.isp;

import java.util.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 29.03.2019
 */
public class Menu {

    private List<Item> roots = new ArrayList<>();
    private Map<String, Item> items = new HashMap<>();

    public Optional<Item> findBy(String name) {
        Optional<Item> rsl = Optional.empty();
        Queue<Item> data = new LinkedList<>();
        for (Item item : this.roots) {
            data.offer(item);
        }
        while (!data.isEmpty()) {
            Item item = data.poll();
            if (item.getName().equals(name)) {
                rsl = Optional.of(item);
                break;
            }
            for (Item child : item.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    public boolean add(Item item) {
        boolean result = false;
        if (item.getParent() == null) {
            this.roots.add(item);
            this.items.put(this.getItemNo(item), item);
            result = true;
        } else {
        Optional<Item> parentItem = this.findBy(item.getParent().getName());
        if (!this.findBy(item.getName()).isPresent() && parentItem.isPresent()) {
            parentItem.get().add(item);
            this.items.put(this.getItemNo(item), item);
            result = true;
            }
        }
        return result;
    }

    public List<Item> getRoots() {
        return roots;
    }

    private String getItemNo(Item item) {
        String regular = "[^0-9,\\.]+";
        return item.getName().replaceAll(regular, "").trim();
    }

    public Map<String, Item> getItems() {
        return this.items;
    }

    public void showMenu() {
        System.out.println("Для завершения работы введите exit");
        for (Item item : this.roots) {
            System.out.println(item.getName());
            checkItem(item);
        }
        System.out.println("Введите номер пункта меню");
    }

    public void checkItem(Item item) {
        if (item.leaves().size() != 0) {
            for (Item child : item.leaves()) {
                System.out.print("----".repeat(child.getLevel()));
                System.out.print(" ");
                System.out.println(child.getName());
                checkItem(child);
            }
        }
    }
}