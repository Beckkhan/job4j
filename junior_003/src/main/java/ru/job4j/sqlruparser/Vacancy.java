package ru.job4j.sqlruparser;

import java.time.LocalDateTime;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 15.03.2019
 */
public class Vacancy {
    private String vacancyName;
    private String description;
    private String link;
    private LocalDateTime actualDate;

    @Override
    public String toString() {
        return String.format(
                " POSITION: %s\n DESCRIPTION: %s\n LINK: %s\n ACTUAL DATE: %s",
                vacancyName,
                description,
                link,
                actualDate
        );
    }

    public Vacancy(String vacancyName, String description, String link, LocalDateTime actualDate) {
        this.vacancyName = vacancyName;
        this.description = description;
        this.link = link;
        this.actualDate = actualDate;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public LocalDateTime getActualDate() {
        return actualDate;
    }

    public void setActualDate(LocalDateTime actualDate) {
        this.actualDate = actualDate;
    }
}