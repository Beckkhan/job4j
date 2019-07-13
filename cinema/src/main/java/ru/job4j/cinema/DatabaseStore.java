package ru.job4j.cinema;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 17.07.2019
 */
public class DatabaseStore implements Store {

    private static final Logger LOGGER = LogManager.getLogger(DatabaseStore.class.getName());

    private static final BasicDataSource SOURCE = new BasicDataSource();

    private final static Store INSTANCE = new DatabaseStore();

    private DatabaseStore() {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("app.properties")) {
            Properties properties = new Properties();
            if (inputStream == null) {
                throw new IllegalStateException("File app.properties not found.");
            }
            properties.load(inputStream);
            SOURCE.setUrl(properties.getProperty("url"));
            SOURCE.setUsername(properties.getProperty("username"));
            SOURCE.setPassword(properties.getProperty("password"));
            SOURCE.setDriverClassName(properties.getProperty("driver-class-name"));
            SOURCE.setMinIdle(5);
            SOURCE.setMaxIdle(10);
            SOURCE.setMaxOpenPreparedStatements(100);
        } catch (IOException e) {
            throw new IllegalStateException("IO problems detected.");
        } catch (NumberFormatException e) {
            throw new IllegalStateException("The data in the file app.properties is incorrect.");
        }
    }

    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Seat> getSeats() {
        List<Seat> result = new ArrayList<>();
        try (
                Connection connection = SOURCE.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery("select * from hall;");
            while (rs.next()) {
                int row = rs.getInt("row");
                int place = rs.getInt("place");
                int price = rs.getInt("price");
                boolean sold = rs.getBoolean("sold");
                Seat seat = new Seat(row, place, price, sold);
                result.add(seat);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Seat getSeat(Seat seat) {
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement ps = connection.prepareStatement(
                        "select * from hall where row=? and place=?;"
                )
        ) {
            ps.setInt(1, seat.getRow());
            ps.setInt(2, seat.getPlace());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int row = rs.getInt("row");
                int place = rs.getInt("place");
                int price = rs.getInt("price");
                boolean sold = rs.getBoolean("sold");
                return new Seat(row, place, price, sold);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        throw new IllegalStateException("There is no seat with this parameters");
    }

    @Override
    public boolean makePayment(Visitor visitor, Seat seat) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection()) {

            connection.setAutoCommit(false);
            try (PreparedStatement first = connection.prepareStatement(
                    "select * from hall where row=? and place=?;"
            )) {
                first.setInt(1, seat.getRow());
                first.setInt(2, seat.getPlace());
                ResultSet resultSet = first.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    try (PreparedStatement second = connection.prepareStatement("update hall set sold=? where id=?;")) {
                        second.setBoolean(1, true);
                        second.setInt(2, id);
                        result = second.executeUpdate() == 1;
                    }
                    if (result) {
                        try (PreparedStatement third = connection.prepareStatement(
                                "insert into visitors(visitor_name, phone, seat_id) values (?, ?, ?);"
                        )) {
                            third.setString(1, visitor.getName());
                            third.setString(2, visitor.getPhone());
                            third.setInt(3, id);
                            third.executeUpdate();
                        }
                        connection.commit();
                    } else {
                        connection.rollback();
                    }
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
                connection.rollback();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }
}