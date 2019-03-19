package ru.job4j.ocp;

import ru.job4j.interactcalc.InteractCalc;

import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 19.03.2019
 */
public class EngineeringCalculator extends InteractCalc {

    /**
     * Our calculator has been enhanced to calculate the cosine and sine functions.
     * We introduce them to our HashMap.
     */
    @Override
    protected void setOperations() {
        super.setOperations();
        load("sin", functionSinus());
        load("cos", functionCosinus());
    }

    /**
     * This function uses the Math class with a call to the sin method,
     * which takes the value of our operand in degrees,
     * converts it to radians and calculates the value of the trigonometric function sine.
     * @return BiConsumer
     */
    private BiConsumer<Double, Double> functionSinus() {
        return (operandFirst, operandSecond) ->
                this.result = Math.sin(Math.toRadians(operandFirst));
    }

    /**
     * This function uses the Math class with a call to the cos method,
     * which takes the value of our operand in degrees,
     * converts it to radians and calculates the value of the trigonometric function cosine.
     * @return BiConsumer
     */
    private BiConsumer<Double, Double> functionCosinus() {
        return (operandFirst, operandSecond) ->
                this.result = Math.cos(Math.toRadians(operandFirst));
    }

    /**
     * We extend the possibilities of the method
     * for the correct calculation of the cosine and sine.
     */
    @Override
    public boolean execute(String expression) {
        boolean isCorrect = this.checkExpression(expression);
        if (isCorrect) {
            if (expression.contains("cos") || expression.contains("sin")) {
                String operation = expression.substring(0, 3);
                String getOperand = expression.substring(3);
                Double operandFirst;
                if (getOperand.isEmpty() && this.result != null) {
                    operandFirst = this.result;
                    System.out.println("Рассчитываем " + operation + " угла " + operandFirst);
                    this.operations.get(operation).accept(operandFirst, null);
                } else {
                    operandFirst = Double.valueOf(getOperand);
                    System.out.println("Рассчитываем " + operation + " угла " + operandFirst);
                    this.operations.get(operation).accept(operandFirst, null);
                    this.result = calculator.getResult();
                }
            } else {
                return super.execute(expression);
            }
        }
        return isCorrect;
    }

    /**
     * We extend the capabilities of the method
     * for the correct verification of new operators available for input.
     */
    @Override
    protected boolean checkExpression(String expression) {
        boolean result = false;
        if (expression.contains("cos") || expression.contains("sin")) {
            Pattern p = Pattern.compile("[cosin]{3}\\d*+");
            Matcher m = p.matcher(expression);
            result = m.find();
        } else {
            result = super.checkExpression(expression);
        }
        return result;
    }

    public static void main(String[] args) {
        EngineeringCalculator engineeringCalculator = new EngineeringCalculator();
        engineeringCalculator.run();
    }
}