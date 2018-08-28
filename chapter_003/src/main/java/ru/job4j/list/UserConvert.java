package ru.job4j.list;

import java.util.HashMap;
import java.util.List;

public class UserConvert {
    /**
     * Метод process принимает list и конвертирует его в Map с ключом Integer id и соответствующим ему User
     * @param list принимает в себя список пользователей
     * @return
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user : list) {
            result.put(user.getId(), user);
        }
        return result;
    }
}
