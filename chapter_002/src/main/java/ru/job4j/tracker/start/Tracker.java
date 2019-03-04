package ru.job4j.tracker.start;

import ru.job4j.tracker.models.*;
import ru.job4j.tracker.ITracker;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 10.0
 * @since 03.03.2019
 */
public class Tracker implements ITracker {

    /** items - список заявок, теперь используем ArrayList вместо обычного массива */
    private final List<Item> items = new ArrayList<>();
    private static final Random RN = new Random();

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    public Item findById(String id) {
        return this.items.stream().filter(item -> item.getId().equals(id))
                .findFirst().orElse(null);
        /*Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;*/
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
        return this.items.stream().filter(item -> item.getName().equals(key))
                .collect(Collectors.toList());
        /*ListIterator<Item> iterator = this.items.listIterator();
        ArrayList<Item> result = new ArrayList<>();
        Item item;
        while (iterator.hasNext()) {
            item = iterator.next();
            if (item.getName().equals(key)) {
                result.add(item);
            }
        }
        return result;*/
    }

    private String generateId() {
        return String.valueOf(RN.nextInt(100));
    }

    public List<Item> getAll() {
        return this.items;
    }
}