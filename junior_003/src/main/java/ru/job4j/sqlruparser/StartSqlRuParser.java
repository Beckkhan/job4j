package ru.job4j.sqlruparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 15.03.2019
 */
public class StartSqlRuParser {

    private ParserConfig parserConfig = new ParserConfig();
    private String cronExpression = parserConfig.get("cron.time");

    private final static Logger LOGGER = LogManager.getLogger(SqlRuDatabase.class);

    public void doParsing() throws SchedulerException {
        JobDetail job = JobBuilder.newJob(SqlRuJob.class).build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("CronTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

    public static void main(String[] args) {
        StartSqlRuParser startSqlRuParser = new StartSqlRuParser();
        try {
            startSqlRuParser.doParsing();
        } catch (SchedulerException e) {
            LOGGER.error("ERROR", e);
        }
    }
}