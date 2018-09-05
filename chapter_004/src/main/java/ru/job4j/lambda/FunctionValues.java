package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс FunctionValues с методами для подсчета функций в диапазоне
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1
 * @since 05.09.2018
 * */
public class FunctionValues {

    interface Function<T> {
        T calculate(T t);
    }

    List<Double> diapason(int start, int end, Function<Double> func) {
        List<Double> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            result.add(func.calculate((double) i));
        }
        return result;
    }

    List<Double> linear(int start, int end, double k, double b) {
        return diapason(start, end, x -> x * k + b);
    }

    List<Double> quadratic(int start, int end, double a,  double b, double c) {
        return diapason(start, end, x -> a * x * x + b * x + c);
    }

    /**
     * В математике логарифм от числа по основанию равен результату деления
     * натурального логарифма от числа на натуральный логарифм от основания .
     */
    List<Double> logarithmic(int start, int end, double a) {
        return diapason(start, end, x -> Math.exp(x) / Math.exp(a));
    }
}
