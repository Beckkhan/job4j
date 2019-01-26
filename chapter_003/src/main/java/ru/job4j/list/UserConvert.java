package ru.job4j.list;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 2.0
 * @since 28.08.2018
 */
public class UserConvert {
    /**
     * Метод process принимает list и конвертирует его в Map
     * с ключом Integer id и соответствующим ему User
     * @param list принимает в себя список пользователей
     * @return
     */
    public HashMap<Integer, User> process(List<User> list) {
        return list.stream().collect(
                Collectors.toMap(User::getId, user -> user, (a, b) -> a, HashMap::new)
        );
        /*HashMap<Integer, User> result = new HashMap<>();
        for (User user : list) {
            result.put(user.getId(), user);
        }
        return result;*/
    }
}
