package ru.job4j.nonblockingalgoritm;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 13.01.2019
 */
public class Storage {
    ConcurrentHashMap<Integer, Base> storage = new ConcurrentHashMap<>();

    public void add(Base item) {
        storage.put(item.getId(), item);
    }

    public void update(Base item) {
        storage.computeIfPresent(item.getId(),
                (key, value) -> {
            if (item.getVersion() != value.getVersion()) {
                throw new OptimisticException();
            }
            value.modify();
            return value;
            }
        );
    }

    public boolean delete(Base item) {
        return storage.remove(item.getId(), item);
    }
}