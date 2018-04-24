package ru.job4j.loop;

/**
 * 4.2. Создать программу вычисляющую факториал.
 */
public class Factorial {

	/**
	 * Считаем факториал, учитывая, что 0!=1.
	 *
	 * @param n - число, факториал которого мы рассчитываем
	 * @return factorial рассчатанное значение факториала
	 */
	public int calc(int n) {
		int factorial = 1;
		for (int i = 1; i <= n; i++) {
			factorial *= i;
		}
		return factorial;
	}
}