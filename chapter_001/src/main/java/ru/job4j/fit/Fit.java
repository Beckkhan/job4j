package ru.job4j.fit;

/**
 * Программа расчета идеального веса.
 */
public class Fit {

    /**
     * Идеальный вес для мужщины.
     * @param height Рост.
     * @return manweight идеальный вес.
     */
    public double manWeight(double height) {
        double manweight = (height - 100) * 1.15;
		return manweight;
    }

    /**
     * Идеальный вес для женщины.
     * @param height Рост.
     * @return womanweight идеальный вес.
     */
    public double womanWeight(double height) {
        double womanweight = (height - 110) * 1.15;
		return womanweight;
    }
}