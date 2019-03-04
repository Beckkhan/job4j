package ru.job4j.tracker;

import ru.job4j.tracker.models.Item;
import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 03.03.2019
 */
public interface ITracker {
    Item add(Item item);
    void replace(String id, Item item);
    void delete(String id);
    List<Item> getAll();
    List<Item> findByName(String key);
    Item findById(String id);
}