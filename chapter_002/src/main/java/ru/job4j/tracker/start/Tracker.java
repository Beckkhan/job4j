package ru.job4j.tracker.start;

import ru.job4j.tracker.models.*;
import java.util.*;

public class Tracker {

    private int position = 0;
    private Item[] items = new Item[10];
    private static final Random RN = new Random();

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
        return item;
    }

    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    public void replace(String id, Item item) {
        for (int index = 0; index < this.items.length; index++) {
            if (this.items[index] != null && this.items[index].getId().equals(id)) {
                this.items[index] = item;
                break;
            }
        }
    }

    public void delete(String id) {
        for (int index = 0; index < this.position; index++) {
            if (id != null && this.items[index].getId().equals(id)) {
                System.arraycopy(this.items, index + 1, this.items, index, this.position - 1 - index);
                this.position--;
                break;
            }
        }
    }

    public Item[] findByName(String key) {
        Item[] names = new Item[this.position];
        int k = 0;
        for (int index = 0; index < this.position; index++) {
            if (key != null && this.items[index].getName().equals(key)) {
                names[k] = items[index];
                k++;
            }
        }
        Item[] result = new Item[k];
        System.arraycopy(names, 0, result, 0, k);
        return result;
    }

    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    public Item[] getAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }
}