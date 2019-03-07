package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.models.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 3.0
 * @since 07.03.2019
 */
public class TrackerSQLTest {

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc"));
            assertThat(tracker.findByName("name").size(), is(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*@Test
    public void checkConnection() {
        TrackerSQL trackerSQL = new TrackerSQL();
        assertThat(trackerSQL.init(), is(true));
    }*/

    @Test
    public void whenAddItemThanFindSameItem() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            String id = trackerSQL.add(new Item("item1", "description1")).getId();
            assertThat(trackerSQL.findByName("item1").size(), is(1));
        }
    }

    @Test
    public void whenDeleteItemThenCantFind() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            String id = trackerSQL.add(new Item("item2", "description2")).getId();
            trackerSQL.delete(id);
            assertThat(trackerSQL.getAll().size(), is(0));
        }
    }

    @Test
    public void whenReplaceThanHaveNewItem() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            String newItemId = trackerSQL.add(new Item("item3", "description3")).getId();
            System.out.println(newItemId);
            trackerSQL.replace(newItemId, new Item("newitem3", "newdescription3"));
            assertThat(trackerSQL.findById(newItemId).getName(), is("newitem3"));
        }
    }
}