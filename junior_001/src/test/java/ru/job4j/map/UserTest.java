package ru.job4j.map;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 3.0
 * @since 21.10.2018
 */
public class UserTest {
    Calendar birthday = new GregorianCalendar(2018, 10, 18);
    private User first = new User("Kolobok", 3, birthday);
    private User second = new User("Kolobok", 3, birthday);

    /**
     * В данном методе мы используем коллекцию HashMap.
     * В качестве уникального ключа используются созданные нами объекты User,
     * которые идентичны друг другу, т.к. имеют одинаковые значения полей.
     * Соответственно (при переопреленных методах equals и hashCode!!!),
     * при добавлении в коллекцию второго объекта, но с ключом
     * second, идентичным ключу first, мы затираем значение 1 на 2, что отражено в assertTrue.
     *
     * При отсутствии перекрытия методов equals и hashCode объекты не воспринимаются как
     * идентичные, и при этом в коллекцию добавляются оба значения. Фактически, перед тестированием
     * мы создали объект first и second, они идентичны, но это ссылки на два объекта, которые имеют
     * каждый свой адрес в памяти, соответственно разное значение при вызове hashCode.
     *
     * При перекрытии только метода hashCode у ключа first и second хэш-коды становятся равны,
     * но при этом отсутствует корректное переопределение метода equals. В данном случае равенство
     * хэш-кодов не означает равенство ключей, т.к. для полноценной идентификации ключей как уникальных
     * необходимо также переопределять в том числе и метод equals. По этой причине повторяется ситуация,
     * при которой ключи не воспринимаются как идентичные.
     */
    @Test
    public void whenCreateTwoEqualUsersAndAddToMapThenCheck() {
        Map<User, Object> users = new HashMap<>();
        users.put(first, 1);
        users.put(second, 2);
        System.out.println(users);
        System.out.println(first.hashCode() + " " + second.hashCode());
        assertThat(users.size(), is(2));
        assertTrue(users.containsValue(1) && users.containsValue(2));
    }

    /**
     * В данном методе ((при переопреленных методах equals и hashCode!!!)
     * используется коллекция HashSet, которая по умолчанию не принимает дубликаты:
     * в нашем случае 2 объекта User являются идентичными друг другу,
     * т.к. имеют одинаковые значения полей.
     *
     * При отсутствии перекрытия методов equals и hashCode объекты не воспринимаются как
     * идентичные, и при этом в коллекцию добавляются оба значения. Фактически, перед тестированием
     * мы создали объект first и second, они идентичны, но это ссылки на два объекта, которые имеют
     * каждый свой адрес в памяти, соответственно разное значение при вызове hashCode.
     *
     * При перекрытии только метода hashCode у ключа first и second хэш-коды становятся равны,
     * но при этом отсутствует корректное переопределение метода equals. В данном случае равенство
     * хэш-кодов не означает равенство ключей, т.к. для полноценной идентификации ключей как уникальных
     * необходимо также переопределять в том числе и метод equals. По этой причине повторяется ситуация,
     * при которой ключи не воспринимаются как идентичные.
     */
    @Test
    public void whenCreateTwoEqualUsersAndAddToHashSetThenCheck() {
        Set<User> users = new HashSet<>();
        users.add(first);
        users.add(second);
        System.out.println(users);
        assertThat(users.size(), is(2));
    }
}