package ru.job4j.tracker.start;

import org.junit.Test;
import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.models.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 2.0
 * @since 03.03.2019
 */
public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        ITracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.getAll().get(0), is(item));
    }

    @Test
    public void whenFindByName() {
        ITracker tracker = new Tracker();
        Item one = new Item("one", "testOne", 1L);
        Item two = new Item("two", "testTwo", 2L);
        Item three = new Item("one", "testThree", 3L);
        Item four = new Item("four", "testFour", 4L);
        Item five = new Item("five", "testFive", 5L);
        tracker.add(one);
        tracker.add(two);
        tracker.add(three);
        tracker.add(four);
        tracker.add(five);
        List<Item> expect = new ArrayList<>();
        expect.add(one);
        expect.add(three);
        List<Item> result = tracker.findByName("one");
        assertThat(result, is(expect));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        ITracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        System.out.println(previous.getId() + " " + previous.getName() + " " + previous.getDescription());
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2", 1234L);
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(next.getId(), next);
        System.out.println(next.getId() + " " + next.getName() + " " + next.getDescription());
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteByItemId() {
        ITracker tracker = new Tracker();
        Item first = new Item("first", "test1", 123L);
        Item second = new Item("second", "test2", 234L);
        Item third = new Item("third", "test3", 345L);
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        tracker.delete(second.getId());
        List<Item> expect = new ArrayList<>();
        expect.add(first);
        expect.add(third);
        List<Item> result = tracker.getAll();
        assertThat(result, is(expect));
    }
}