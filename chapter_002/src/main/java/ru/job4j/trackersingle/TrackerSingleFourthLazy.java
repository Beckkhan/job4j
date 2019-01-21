package ru.job4j.trackersingle;

import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.start.Tracker;

import java.util.List;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 21.01.2018
 */
public class TrackerSingleFourthLazy {
    private Tracker tracker;

    private TrackerSingleFourthLazy(Tracker tracker) {
        this.tracker = tracker;
    }

    public static TrackerSingleFourthLazy getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final TrackerSingleFourthLazy INSTANCE = new TrackerSingleFourthLazy(new Tracker());
    }

    public Item add(Item item) {
        return this.tracker.add(item);
    }

    public Item findById(String id) {
        return this.tracker.findById(id);
    }

    public void replace(String id, Item item) {
        this.tracker.replace(id, item);
    }

    public void delete(String id) {
        this.tracker.delete(id);
    }

    public List<Item> findByName(String key) {
        return this.tracker.findByName(key);
    }

    public List<Item> getAll() {
        return this.tracker.getAll();
    }
}