package ru.job4j.sqlruparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.ConnectionRollback;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Properties;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 15.03.2019
 */
public class SqlRuDatabaseTest {

    private static final Logger LOG = LogManager.getLogger(SqlRuDatabaseTest.class);
    private LocalDateTime actualDate = LocalDateTime.of(2019, Month.MARCH, 7, 0, 0);
    private Vacancy javaDeveloper;


    public Connection init() {
        try (InputStream in = SqlRuDatabase.class.getClassLoader().getResourceAsStream("app.properties")) {
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

    @Before
    public void beforeTest() {
        this.javaDeveloper = new Vacancy("Java1", "software development", "www.sql.ru", this.actualDate);
    }

    @Test
    public void addVacancy() {
        try (SqlRuDatabase sqlRuDatabase = new SqlRuDatabase(ConnectionRollback.create(init()))) {
            Vacancy result = sqlRuDatabase.addVacancy(this.javaDeveloper);
            assertThat(result, is(javaDeveloper));
        } catch (SQLException e) {
            this.LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void getAll() {
        try (SqlRuDatabase sqlRuDatabase = new SqlRuDatabase(ConnectionRollback.create(init()))) {
            List<Vacancy> result = sqlRuDatabase.getAll();
            assertThat(result, is(notNullValue()));
        } catch (SQLException e) {
            this.LOG.error(e.getMessage(), e);
        }
    }
}
