package ru.job4j.stream;

import java.util.Comparator;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 30.01.2019
 */
public class Student implements Comparator<Student> {

    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public int compare(Student first, Student second) {
        return first.getName().compareTo(second.getName());
    }
}