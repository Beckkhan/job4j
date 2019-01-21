package ru.job4j.trackersingle;

import org.junit.Test;
import ru.job4j.tracker.models.Item;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 21.01.2018
 */
public class TrackerSingleSecondLazyTest {
    @Test
    public void whenCheckSingletonForStaticFieldLazy() {
        TrackerSingleSecondLazy trackerFirst = TrackerSingleSecondLazy.getInstance();
        TrackerSingleSecondLazy trackerSecond = TrackerSingleSecondLazy.getInstance();
        assertThat(trackerFirst, is(trackerSecond));
        Item item = new Item("test1", "testDescription", 123L);
        Item item2 = new Item("test2", "testDescription2", 1234L);
        trackerFirst.add(item);
        trackerSecond.add(item2);
        assertThat(trackerFirst.getAll().get(0), is(trackerSecond.getAll().get(0)));
        assertThat(trackerFirst.getAll().get(1), is(item2));
    }
}