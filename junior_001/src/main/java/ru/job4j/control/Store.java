package ru.job4j.control;

import java.util.List;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 25.11.2018
 */
public class Store {

    public static class User {
        int id;
        String name;

        public User( int id, String name ) {
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
        if(current.isEmpty()) {
            info.deleted = previous.size();
        } else {
            int position = 0;
            for(User user : previous) {
                if(current.get(position).id == user.id) {
                    if (!current.get(position).name.equals(user.name)) {
                        info.changed++;
                    }
                } else {
                    info.deleted++;
                }
                position++;
            }
            info.added = current.size() - previous.size() + info.deleted;
        }
        return info;
    }
}
