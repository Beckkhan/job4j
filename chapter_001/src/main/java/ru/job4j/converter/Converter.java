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
        int result = value / 60;
        return result;
    }

    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro(int value) {
        int result = value / 70;
		return result;
    }
}