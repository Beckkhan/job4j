package ru.job4j.max;

/**
 * 3.2. Максимум из двух чисел.
 */
public class Max {

    /**
     * Находим максимальное число из двух.
     *
     * @param first,second сравнивемые числа.
     * @return maximum
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }
	public int maxofthree(int first, int second, int third) {
        int temp = this.max(this.max(first, second), third);
        return temp;
	}
}