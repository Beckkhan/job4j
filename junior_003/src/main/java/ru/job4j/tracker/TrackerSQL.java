package ru.job4j.tracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.job4j.tracker.models.Item;
import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 4.0
 * @since 07.03.2019
 */
public class TrackerSQL implements ITracker, AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger(TrackerSQL.class.getName());

    private Connection connection;

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    /*public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            Class.forName(properties.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
            this.setTableItems();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return this.connection != null;
    }*/

    /*public void setTableItems() {
        try (PreparedStatement prepStatement =
                     this.connection.prepareStatement(
                             "create table if not exists items (id serial primary key, item_name varchar(200), description text, created timestamp, comments text);")
        ) {
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }*/

    @Override
    public Item add(Item item) {
        try (PreparedStatement prepStatement = connection.prepareStatement("insert into items(item_name, description, created, comments) values (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {
            prepStatement.setString(1, item.getName());
            prepStatement.setString(2, item.getDescription());
            prepStatement.setTimestamp(3, new Timestamp(item.getCreate()));
            prepStatement.setString(4, item.getComments());
            prepStatement.executeUpdate();
            ResultSet generatedKeys = prepStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                item.setId(String.valueOf(generatedKeys.getInt(1)));
                System.out.println(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return item;
    }

    @Override
    public void replace(String id, Item item) {
        try (PreparedStatement prepStatement = connection.prepareStatement("update items set item_name = ?, description = ?, created = ?, comments = ? where id = ?;")) {
            prepStatement.setString(1, item.getName());
            prepStatement.setString(2, item.getDescription());
            prepStatement.setTimestamp(3, new Timestamp(item.getCreate()));
            prepStatement.setString(4, item.getComments());
            prepStatement.setInt(5, Integer.parseInt(id.trim()));
            prepStatement.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement prepStatement = connection.prepareStatement("delete from items where id = ?;")) {
            prepStatement.setInt(1, Integer.parseInt(id.trim()));
            prepStatement.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Item> getAll() {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement prepStatement = this.connection.prepareStatement("select * from items;")) {
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                result.add(new Item(
                                rs.getString("item_name"),
                                rs.getString("description"),
                                rs.getString(String.valueOf("id"))
                        )
                );
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement prepStatement = this.connection.prepareStatement("select * from items where item_name = ?;")) {
            prepStatement.setString(1, key);
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                result.add(new Item(
                                rs.getString("item_name"),
                                rs.getString("description"),
                                rs.getString(String.valueOf("id"))
                        )
                );
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        try (PreparedStatement prepStatement = this.connection.prepareStatement("select * from items where id = ?;")) {
            prepStatement.setInt(1, Integer.parseInt(id.trim()));
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                item = new Item(
                        rs.getString("item_name"),
                        rs.getString("description"),
                        rs.getString(String.valueOf("id"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void close() throws Exception {
        if (this.connection != null) {
            this.connection.close();
        }
    }
}