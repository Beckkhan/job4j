package ru.job4j.interactcalc;

import ru.job4j.calculator.Calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 5.0
 * @since 21.03.2019
 */
public class InteractCalc {

    public static final Pattern ICPATTERN = Pattern.compile("\\d*+[+\\/\\*-]+\\d+");
    private final Calculator calculator;
    private final Scanner scanner;
    private Double result;
    private Map<String, BiConsumer<Double, Double>> operations = new HashMap<>();

    /**
     * Creating a constructor.
     */
    public InteractCalc() {
        this.scanner = new Scanner(System.in);
        this.calculator = new Calculator();
        this.loadActs();
    }

    /**
     * Creating a HashMap to store operations:
     * the key is a string with an operator (addition +, subtraction -, division /, multiplication *),
     * the value for the key will be the corresponding functional interface,
     * in our case a function that does not return value - BiConsumer.
     */
    public void loadActs() {
        this.operations.put("+", functionAdd());
        this.operations.put("-", functionSubtract());
        this.operations.put("/", functionDiv());
        this.operations.put("*", functionMultiple());
    }

    /**
     * The addition function refers to the Calculator class.
     * This refers to the appropriate method in the Calculator class.
     * @return BiConsumer
     */
    private BiConsumer<Double, Double> functionAdd() {
        return calculator::add;
    }

    /**
     * The subtraction function refers to the Calculator class.
     * This refers to the appropriate method in the Calculator class.
     * @return BiConsumer
     */
    private BiConsumer<Double, Double> functionSubtract() {
        return calculator::subtract;
    }

    /**
     * The division function refers to the Calculator class.
     * This refers to the appropriate method in the Calculator class.
     * @return BiConsumer
     */
    private BiConsumer<Double, Double> functionDiv() {
        return calculator::div;
    }

    /**
     * The multiplication function refers to the Calculator class.
     * This refers to the appropriate method in the Calculator class.
     * @return BiConsumer
     */
    private BiConsumer<Double, Double> functionMultiple() {
        return calculator::multiple;
    }

    /**
     * This method processes the expression entered by the user.
     * Using a regular expression, we find out which operator and operands are present.
     * @param expression
     * @return boolean
     */
    public boolean execute(String expression) {
        boolean isCorrect = this.checkExpression(expression);
        if (isCorrect) {
            Pattern pattern = Pattern.compile("[\\+\\/\\*\\-]");
            Matcher matcher = pattern.matcher(expression);
            matcher.find();
            int index = matcher.start();
            if (index == 0 && this.result != null) {
                var getValue = expression.substring(1).trim();
                double operandSecond = Double.valueOf(getValue);
                this.operations.get(expression.substring(index, index + 1)).accept(
                        result,
                        operandSecond);
                this.result = calculator.getResult();
            } else {
                var getFirstValue = expression.substring(0, index).trim();
                double operandFirst = Double.valueOf(getFirstValue);
                var getSecondValue = expression.substring(index + 1).trim();
                double operandSecond = Double.valueOf(getSecondValue);
                this.operations.get(expression.substring(index, index + 1)).accept(
                        operandFirst,
                        operandSecond
                );
                this.result = calculator.getResult();
            }
        }
        return isCorrect;
    }

    /**
     * With this method, we display the result of the calculation on the screen.
     */
    public void printResult(boolean valid) {
        if (valid) {
            System.out.println(this.result);
        } else {
            System.out.println("You entered an invalid expression.");
        }
    }

    /**
     * Here the user's expression is checked for the correctness of the entered information.
     * @param expression
     * @return boolean
     */
    public boolean checkExpression(String expression) {
        Matcher m = ICPATTERN.matcher(expression);
        return m.find();
    }

    /**
     * This method contains the main loop "while" in which the entire program is running.
     */
    public void run() {
        System.out.println("Enter an expression to calculate in the format a+b without spaces");
        String expression = scanner.nextLine();
        while (!"exit".equals(expression)) {
            boolean valid = this.execute(expression);
            this.printResult(valid);
            expression = scanner.nextLine();
        }
        System.out.println("Goodbye!");
    }

    /**
     * Main method to start the program.
     */
    public static void main(String[] args) {
        InteractCalc iCalc = new InteractCalc();
        iCalc.run();
    }

    /**
     * The getter for the variable result.
     */
    public Double getResult() {
        return this.result;
    }

    /**
     * The setter for the variable result.
     */
    public void setResult(Double result) {
        this.result = result;
    }

    /**
     * The getter for the variable operations.
     */
    public Map<String, BiConsumer<Double, Double>> getOperations() {
        return this.operations;
    }

    /**
     * The getter for the variable calculator.
     */
    public Calculator getCalculator() {
        return calculator;
    }
}