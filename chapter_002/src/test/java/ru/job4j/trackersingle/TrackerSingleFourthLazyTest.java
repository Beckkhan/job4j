package ru.job4j.trackersingle;

import org.junit.Test;
import ru.job4j.tracker.models.Item;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerSingleFourthLazyTest {
    @Test
    public void whenCheckSingletonForPrivateStaticFinalClassLazy() {
        TrackerSingleFourthLazy trackerFirst = TrackerSingleFourthLazy.getInstance();
        TrackerSingleFourthLazy trackerSecond = TrackerSingleFourthLazy.getInstance();
        assertThat(trackerFirst, is(trackerSecond));
        Item item = new Item("test1", "testDescription", 123L);
        Item item2 = new Item("test2", "testDescription2", 1234L);
        trackerFirst.add(item);
        trackerSecond.add(item2);
        assertThat(trackerFirst.getAll().get(0), is(trackerSecond.getAll().get(0)));
        assertThat(trackerFirst.getAll().get(1), is(item2));
    }
}