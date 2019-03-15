package ru.job4j.sqlruparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 15.03.2019
 */
public class SqlRuJob implements Job {

    private static final Logger LOGGER = LogManager.getLogger(StartSqlRuParser.class);
    private ParserConfig parserConfig = new ParserConfig();
    private SqlRuParser sqlRuParser = new SqlRuParser(this.connectSqlRuDatabase());


    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        this.LOGGER.info("Parsing process started");
        this.sqlRuParser.doNextParsing();
        this.LOGGER.info("Parsing process stopped");
    }

    public Connection connectSqlRuDatabase() {
        try {
            return DriverManager.getConnection(
                    this.parserConfig.get("url"),
                    this.parserConfig.get("username"),
                    this.parserConfig.get("password")
            );
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}