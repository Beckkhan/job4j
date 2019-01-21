package ru.job4j.lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class CalculatorLambda {
    public interface Operation {
        double calc(int left, int right);
    }

    public void multiple(int start, int finish, int value, BiFunction<Integer, Integer, Double> op,
                         Consumer<Double> media) {
        for (int index = start; index != finish; index++) {
            media.accept(op.apply(value, index));
        }
    }

    /*public static void main(String[] args) {
        CalculatorLambda calc = new CalculatorLambda();
        calc.multiple(
                0, 10, 2, (val, ind) -> {
                    double result = val * ind;
                    System.out.printf("Multi %s * %s = %s %n", val, ind, result);
                    return result;
                }, result -> System.out.println(result)
        );
    }*/
}