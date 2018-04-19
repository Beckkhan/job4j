package ru.job4j.converter;

/**
 * Конвертер валюты.
 */
public class Converter {

    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллоры
     */
    public int rubleToDollar(int value) {
        int dollars = value / 60;
        return dollars;
    }

    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro(int value) {
        int euros = value / 70;
		return euros;
    }

    /**
     * Конвертируем доллары в рубли.
     * @param value доллары.
     * @return Рубли.
     */
    public int dollarToRub(int value) {
        int rub = value * 60;
        return rub;
    }

    /**
     * Конвертируем евро в рубли.
     * @param value евро.
     * @return Рубли.
     */
    public int eurToRub(int value) {
        int rub = value * 70;
        return rub;
    }
}