package ru.job4j.sqlruparser;

import ru.job4j.jdbc.Config;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 15.03.2019
 */
public class ParserConfig {
    private final Properties properties = new Properties();

    public ParserConfig() {
        this.init();
    }

    public void init() {
        try (InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(inputStream);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.properties.getProperty(key);
    }
}
