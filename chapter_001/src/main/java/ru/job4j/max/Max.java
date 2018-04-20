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
        int maximal = (first > second) ? first : second;
        return maximal;
    }
}