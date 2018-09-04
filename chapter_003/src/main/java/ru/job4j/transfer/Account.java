package ru.job4j.transfer;

/**
 * Класс счета Account
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1
 * @since 04.09.2018
 * */
public class Account {
    private double value;
    private String requisites;

    public Account() {
    }
    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }

    @Override
    public String toString() {
        return String.format("Реквизиты счёта: %. Остаток на счёте: %,.4f", this.requisites, this.value);
    }
}
