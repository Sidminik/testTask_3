package ru.cft.shift.core.statistics.arrays;

import java.util.List;

public class DoubleArrayStats extends ArrayStats {
    private double min;
    private double max;
    private double sum;
    private final double average;

    public DoubleArrayStats(List<Double> doubleArray) {
        super(doubleArray.size());
        min = doubleArray.getFirst();
        max = doubleArray.getFirst();
        sum = doubleArray.getFirst();

        if (count > 1) {
            for (int i = 1; i < count; i++) {
                if (doubleArray.get(i).compareTo(min) < 0) {
                    min = doubleArray.get(i);
                }
                if (doubleArray.get(i).compareTo(max) > 0) {
                    max = doubleArray.get(i);
                }
                sum += doubleArray.get(i);
            }
        }
        average = sum / count;
    }

    @Override
    public void printShortStatistics() {
        System.out.println("Количество записанных вещественных чисел: " + count);
    }

    @Override
    public void printFullStatistics() {
        System.out.println("Количество записанных вещественных чисел: " + count);
        System.out.println("Минимальное вещественное число: " + min);
        System.out.println("Максимальное вещественное число: " + max);
        System.out.println("Сумма вещественных чисел: " + sum);
        System.out.println("Среднее арифметическое вещественных чисел: " + average);
    }
}
