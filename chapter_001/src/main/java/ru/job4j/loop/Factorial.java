package ru.job4j.loop;

/**
 * 4.2. Создать программу вычисляющую факториал.
 */
public class Factorial {

	/**
	 * Считаем факториал, учитывая, что 0!=1.
	 *
	 * @param n
	 * @return factorial
	 */
	public int calc(int n) {
		int factorial = 1;
		if (n == 0) {
			factorial = 1;
		} else {
			for (int i = 1; i <= n; i++) {
				factorial = factorial * i;
			}
		}
		return factorial;
	}
}