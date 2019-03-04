package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.models.Item;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 03.03.2019
 */
public class TrackerSQLTest {

    @Test
    public void checkConnection() {
        TrackerSQL trackerSQL = new TrackerSQL();
        assertThat(trackerSQL.init(), is(true));
    }

    @Test
    public void whenAddItemThanFindSameItem() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL()) {
            String id = trackerSQL.add(new Item("item1", "description1")).getId();
            assertThat(trackerSQL.findById(id).getName(), is("item1"));
        }
    }

    @Test
    public void whenDeleteItemThenCantFind() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL()) {
            String id = trackerSQL.add(new Item("item2", "description2")).getId();
            trackerSQL.delete(id);
            assertThat(trackerSQL.findById(id), nullValue());
        }
    }

    @Test
    public void whenReplaceThanHaveNewItem() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL()) {
            String newItemId = trackerSQL.add(new Item("item3", "description3")).getId();
            System.out.println(newItemId);
            trackerSQL.replace(newItemId, new Item("newitem3", "newdescription3"));
            assertThat(trackerSQL.findById(newItemId).getName(), is("newitem3"));
        }
    }
}