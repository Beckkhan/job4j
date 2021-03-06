package ru.job4j.sqlruparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.sql.Connection;
import java.time.*;
import java.util.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 3.0
 * @since 21.03.2019
 */
public class SqlRuParser {

    private static final Logger LOGGER = LogManager.getLogger(StartSqlRuParser.class.getName());

    private ParserConfig config = new ParserConfig();

    private static List<Vacancy> vacancies = new ArrayList<>();

    private SqlRuDatabase database;

    private boolean stop = false;

    private String year;

    private String day;

    public SqlRuParser(Connection connection) {
        this.database = new SqlRuDatabase(connection);
        this.year = this.config.get("linkYear");
        this.day = this.config.get("linkDay");
        this.doFirstParsing();
    }

    public void toParseVacancyInfo(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();

        Elements tableRaws = doc.getElementsByAttributeValue("class", "postslisttopic");

        for (int i = 0; i < tableRaws.size(); i++) {
            Elements searchTagDate = doc.getElementsByTag("tbody");
            Element inner = searchTagDate.get(3).child(i + 1).child(6);
            String vacancyDate = inner.text();
            LocalDateTime actualDate = this.getDateFromString(vacancyDate);
            if (actualDate.isBefore(LocalDateTime.now().minusYears(1))) {
                stop = true;
                break;
            }
            Element element1 = tableRaws.get(i).child(0);
            String urlOnVacancy = element1.attr("href");
            String name = element1.text();
            Document doc2 = null;
            try {
                doc2 = Jsoup.connect(urlOnVacancy).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements elements2 = doc2.getElementsByAttributeValue("class", "msgBody");
            Element element2 = elements2.get(1);
            String description = element2.text();
            vacancies.add(new Vacancy(name, description, urlOnVacancy, actualDate));
        }
        this.database.addListOfVacancies(vacancies);
        this.vacancies.clear();
        LOGGER.info(String.format("New vacancies found: %s", vacancies.size()));
    }

    private LocalDateTime getDateFromString(String date) {
        String time = date.substring(date.indexOf(",") + 2);
        int hour = Integer.parseInt(time.split(":")[0].trim());
        int min = Integer.parseInt(time.split(":")[1].trim());
        LocalTime localTime = LocalTime.of(hour, min);

        date = date.substring(0, date.indexOf(",")).trim();
        LocalDate localDate = LocalDate.now();

        if (date.contains("вчера")) {
            localDate.minusDays(1);
        } else if (!date.contains("сегодня") && !date.contains("вчера")) {
            int year = Integer.parseInt("20" + date.substring(date.length() - 2));
            String stringMonth = date.substring(2, 6).trim();
            int day = Integer.parseInt(date.substring(0, 2).trim());
            localDate = LocalDate.of(year, getMonthFromDate(stringMonth), day);
        }
        return LocalDateTime.of(localDate, localTime);
    }

    private Month getMonthFromDate(String stringMonth) {
        Month result = Month.JANUARY;
        if ("фев".equals(stringMonth)) {
            result = Month.FEBRUARY;
        } else if ("мар".equals(stringMonth)) {
            result = Month.MARCH;
        } else if ("апр".equals(stringMonth)) {
            result = Month.APRIL;
        } else if ("май".equals(stringMonth) || "мая".equals(stringMonth)) {
            result = Month.MAY;
        } else if ("июн".equals(stringMonth)) {
            result = Month.JUNE;
        } else if ("июл".equals(stringMonth)) {
            result = Month.JULY;
        } else if ("авг".equals(stringMonth)) {
            result = Month.AUGUST;
        } else if ("сен".equals(stringMonth)) {
            result = Month.SEPTEMBER;
        } else if ("окт".equals(stringMonth)) {
            result = Month.OCTOBER;
        } else if ("ноя".equals(stringMonth)) {
            result = Month.NOVEMBER;
        } else if ("дек".equals(stringMonth)) {
            result = Month.DECEMBER;
        }
        return result;
    }

    public void doFirstParsing() {
        try {
            stop = false;
            int i = 0;
            while (!stop) {
                toParseVacancyInfo(this.year + i++);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doNextParsing() {
        stop = false;
        try {
            toParseVacancyInfo(this.day);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}