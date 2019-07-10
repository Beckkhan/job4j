package ru.job4j.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 3.0
 * @since 09.07.2019
 */
public class DbStore implements Store {

    private static final Logger LOGGER = LogManager.getLogger(DbStore.class.getName());

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    public DbStore() {
        SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/webstore");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    @Override
    public User add(User user) {
        try (
                Connection con = SOURCE.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "insert into users(name, login, email, created, password, role, country, city) values(?, ?, ?, now(), ?, ?, ?, ?);",
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole().toString());
            ps.setString(6, user.getCountry());
            ps.setString(7, user.getCity());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(String.valueOf(resultSet.getInt(1)));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        try (
                Connection con = SOURCE.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "update users set name=?, login=?, email=?, password=?, role=?, country=?, city=? where id=?;"
                )
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole().toString());
            ps.setString(6, user.getCountry());
            ps.setString(7, user.getCity());
            ps.setInt(8, Integer.parseInt(user.getId()));
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        boolean result = false;
        try (
                Connection con = SOURCE.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "delete from users where id=?;"
                )
        ) {
            ps.setInt(1, Integer.parseInt(user.getId()));
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public User findById(User user) {
        User result = null;
        try (
                Connection con = SOURCE.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "select * from users where id = ?;"
                )
        ) {
            ps.setInt(1, Integer.parseInt(user.getId()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = new User(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString(String.valueOf("email")),
                        rs.getTimestamp("created").toLocalDateTime().toLocalDate(),
                        Role.valueOf(rs.getString("role"))
                );
                result.setCountry(rs.getString("country"));
                result.setCity(rs.getString("city"));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try (
                Connection con = SOURCE.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "select * from users;"
                )
        ) {
            ResultSet rs = ps.executeQuery();
            User next;
            while (rs.next()) {
                next = new User(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString(String.valueOf("email")),
                        rs.getTimestamp("created").toLocalDateTime().toLocalDate(),
                        Role.valueOf(rs.getString("role"))
                );
                next.setCountry(rs.getString("country"));
                next.setCity(rs.getString("city"));
                result.add(next);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public User findByLogin(User user) {
        User result = null;
        try (
                Connection con = SOURCE.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "select * from users where login = ?;"
                )
        ) {
            ps.setString(1, user.getLogin());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = new User(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString(String.valueOf("email")),
                        rs.getTimestamp("created").toLocalDateTime().toLocalDate(),
                        Role.valueOf(rs.getString("role"))
                );
                result.setCountry(rs.getString("country"));
                result.setCity(rs.getString("city"));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean isCredential(String login, String password) {
        boolean result = false;
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement ps = connection.prepareStatement(
                        "select * from users where login=? and password=?;"
                )
        ) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean resetRole(User user) {
        boolean result = false;
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement ps = connection.prepareStatement(
                        "update users set role=? where id=?"
                )
        ) {
            ps.setString(1, user.getRole().toString());
            ps.setInt(2, Integer.parseInt(user.getId()));
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<String> getCountries() {
        List<String> result = new ArrayList<>();
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement ps = connection.prepareStatement(
                        "select title from country;"
                )
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("title"));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<String> getCitiesByCountry(String country) {
        List<String> result = new ArrayList<>();
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement ps = connection.prepareStatement(
                        "select city.title from city join country on city.country_id = country.id where country.title = ?;"
                )
        ) {
            ps.setString(1, country);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("title"));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }
}