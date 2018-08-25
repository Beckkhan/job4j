package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

public class PhoneDictionary {
    private List<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (!(person != null && (person.getName().equalsIgnoreCase(key)
                                    || person.getSurname().equalsIgnoreCase(key)
                                    || person.getPhone().equalsIgnoreCase(key)
                                    || person.getAddress().equalsIgnoreCase(key)))) {
                System.out.println("По указанным данным информации не найдено.");
                break;
            }
            result.add(person);
        }
        return result;
    }
}