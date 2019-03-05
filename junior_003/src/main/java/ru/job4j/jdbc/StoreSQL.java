package ru.job4j.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 05.03.2019
 */
public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connection;

    public StoreSQL(Config config) {
        this.config = config;
        this.config.init();
    }

    public boolean init() {
        try {
            this.connection = DriverManager.getConnection(config.get("urlSQLLite"));
            if (this.connection != null) {
                this.connection.setAutoCommit(false);
                String dropIfExists = "DROP TABLE if EXISTS entry;";
                String createIfNotExists = "CREATE TABLE if NOT EXISTS entry (field integer);";
                try (Statement statement = this.connection.createStatement()) {
                    statement.execute(dropIfExists);
                    statement.execute(createIfNotExists);
                    this.connection.commit();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return this.connection != null;
    }


    public void generate(int size) {
        String query = "INSERT INTO entry (field) VALUES (?);";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
            for (int i = 1; i <= size; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            this.connection.commit();
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<Entry> load() {
        List<Entry> result = new ArrayList<>();
        try (PreparedStatement preparedStatement  = this.connection.prepareStatement("SELECT field FROM entry;")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(new Entry(rs.getInt("field")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}