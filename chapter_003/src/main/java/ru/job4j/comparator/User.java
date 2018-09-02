package ru.job4j.comparator;

public class User implements Comparable<User> {
    private String name;
    private Integer age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(User o) {
        return this.age.compareTo(o.age);
    }

    public String toString() {
        return String.format("%s : %d", this.name, this.age);
    }
}
