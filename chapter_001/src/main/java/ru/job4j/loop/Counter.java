package ru.job4j.loop;

/**
 * 4.1. Подсчет суммы чётных чисел в диапазоне 1-10.
 */
public class Counter {

    /**
     * Находим четные числа в диапазоне и суммируем.
     *
     * @param диапазон.
     * @return res
     */
    public int add(int start, int finish) {
		int res = 0;
        for (int i = start; i <= finish; i++) {
			if (i % 2 == 0) {
				res = res + i;
			}
		}
		return res;
	}
}