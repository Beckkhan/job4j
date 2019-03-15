package ru.job4j.sqlruparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 15.03.2019
 */
public class SqlRuDatabase implements Closeable {

    private static final Logger LOGGER = LogManager.getLogger(SqlRuDatabase.class.getName());

    private Connection connection;

    private ParserConfig parserConfig = new ParserConfig();

    public SqlRuDatabase(Connection connection) {
        this.connection = connection;
        this.init();
    }

    private void init() {
        String table = parserConfig.get("create.sqlRuDatabase");
        try (Statement st = this.connection.createStatement()) {
            st.executeUpdate(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vacancy addVacancy(Vacancy vacancy) {
        try (PreparedStatement prepStatement = connection.prepareStatement("insert into vacancies(vacancy_name, description, link, actual_date) values (?, ?, ?, ?);")) {
            if (checkForNoDuplicationInfo(vacancy)) {
                prepStatement.setString(1, vacancy.getVacancyName());
                prepStatement.setString(2, vacancy.getDescription());
                prepStatement.setString(4, vacancy.getLink());
                prepStatement.setTimestamp(3, Timestamp.valueOf(vacancy.getActualDate()));
                prepStatement.executeUpdate();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return vacancy;
    }

    public boolean checkForNoDuplicationInfo(Vacancy vacancy) {
        String vacancyName = vacancy.getVacancyName();
        try (PreparedStatement pStat = this.connection
                .prepareStatement("SELECT * FROM vacancies WHERE vacancy_name = ?")) {
            pStat.setString(1, vacancyName);
            ResultSet rs = pStat.executeQuery();
            if (!rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    public List<Vacancy> getAll() {
        List<Vacancy> result = new ArrayList<>();
        try (PreparedStatement prepStatement = this.connection.prepareStatement("select * from vacancies;")) {
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                result.add(new Vacancy(
                                rs.getString("vacancy_name"),
                                rs.getString("description"),
                                rs.getString("link"),
                                rs.getTimestamp("actual_date").toLocalDateTime()
                        )
                );
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    public void addListOfVacancies(List<Vacancy> vacancies) {
        String sql = "INSERT INTO vacancies(vacancy_name, description, link, actual_date) values (?,?,?,?);";
        try (PreparedStatement prepStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            for (Vacancy vacancy : vacancies) {
                prepStatement.setString(1, vacancy.getVacancyName());
                prepStatement.setString(2, vacancy.getDescription());
                prepStatement.setString(3, vacancy.getLink());
                prepStatement.setTimestamp(4, Timestamp.valueOf(vacancy.getActualDate()));
                prepStatement.addBatch();
            }
            prepStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
