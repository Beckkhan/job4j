package ru.job4j.search;

import java.util.LinkedList;
import java.util.stream.Stream;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 4.0
 * @since 26.08.2018
 */
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        if (tasks.isEmpty()) {
            tasks.add(task);
        }
        int index = (int) this.tasks.stream().filter(
                position -> task.getPriority() > position.getPriority()
        ).count();
        this.tasks.add(index, task);
        /*if (tasks.isEmpty()) {
            tasks.add(task);
        }
        for (int i = 0; i < tasks.size(); i++) {
            if (task.getPriority() < tasks.get(i).getPriority()) {
                tasks.add(i, task);
                break;
            } else if (task.getPriority() > tasks.getLast().getPriority()) {
                tasks.addLast(task);
                break;
            }
        }*/
    }

    public Task take() {
        return this.tasks.poll();
    }
}