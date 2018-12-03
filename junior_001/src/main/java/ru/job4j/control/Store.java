package ru.job4j.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 2.0
 * @since 03.11.2018
 */
public class Store {

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, User> currentMap = new HashMap<>();
        for (User user : current) {
            currentMap.put(user.id, user);
        }
        for (User user : previous) {
            if (!currentMap.containsKey(user.id)) {
                info.deleted++;
        } else if (!currentMap.get(user.id).equals(user)) {
                info.changed++;
            }
        }
        info.added = current.size() - previous.size() + info.deleted;
        return info;
    }
}
