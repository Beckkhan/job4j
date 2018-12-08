package ru.job4j.monitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 08.12.2018
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> store = new HashMap<>();

    public synchronized boolean add(User user) {
        return this.store.putIfAbsent(user.getId(), user) == null;
    }
    public synchronized boolean update(User user) {
        return this.store.computeIfPresent(user.getId(), (key, value) -> user) != null;
    }
    public synchronized boolean delete(User user) {
        return this.store.remove(user.getId()) != null;
    }
    public synchronized User getUser(int id) {
        return this.store.get(id);
    }
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        if (this.store.containsKey(fromId)
                && this.store.containsKey(toId)
                && this.store.get(fromId).getAmount() >= amount) {
            User sender = new User(fromId, this.store.get(fromId).getAmount() - amount);
            User recipient = new User(toId, this.store.get(toId).getAmount() + amount);
            this.update(sender);
            this.update(recipient);
            result = true;
        }
        return result;
    }
}
