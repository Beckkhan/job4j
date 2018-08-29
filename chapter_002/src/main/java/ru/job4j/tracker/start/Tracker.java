package ru.job4j.tracker.start;

import ru.job4j.tracker.models.*;
import java.util.*;

public class Tracker {

    /** items - список заявок, теперь используем ArrayList вместо обычного массива */
    private final List<Item> items = new ArrayList<>();
    private static final Random RN = new Random();

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    public void replace(String id, Item item) {
        ListIterator<Item> iterator = this.items.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(item.getId())) {
                iterator.set(item);
                item.setId(id);
                break;
            }
        }
    }

    public void delete(String id) {
        ListIterator<Item> iterator = this.items.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(id)) {
                iterator.remove();
                break;
            }
        }
    }

    public List<Item> findByName(String key) {
        ListIterator<Item> iterator = this.items.listIterator();
        ArrayList<Item> result = new ArrayList<>();
        Item item;
        while (iterator.hasNext()) {
            item = iterator.next();
            if (item.getName().equals(key)) {
                result.add(item);
            }
        }
        return result;
    }

    private String generateId() {
        return String.valueOf(RN.nextInt(100));
    }

    public List<Item> getAll() {
        return this.items;
    }
}