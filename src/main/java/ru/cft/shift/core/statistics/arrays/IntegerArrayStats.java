package ru.cft.shift.core.statistics.arrays;

import java.math.BigInteger;
import java.util.List;

public class IntegerArrayStats extends ArrayStats {
    private BigInteger min;
    private BigInteger max;
    private BigInteger sum;
    private final double average;

    public IntegerArrayStats(List<BigInteger> integerArray) {
        super(integerArray.size());
        min = integerArray.getFirst();
        max = integerArray.getFirst();
        sum = integerArray.getFirst();

        if (count > 1) {
            for (int i = 1; i < count; i++) {
                if (integerArray.get(i).compareTo(min) < 0) {
                    min = integerArray.get(i);
                }
                if (integerArray.get(i).compareTo(max) > 0) {
                    max = integerArray.get(i);
                }
                sum = sum.add(integerArray.get(i));
            }
        }
        average = sum.doubleValue() / count;
    }

    @Override
    public void printShortStatistics() {
        System.out.println("Количество записанных целых чисел: " + count);
    }

    @Override
    public void printFullStatistics() {
        System.out.println("Количество записанных целых чисел: " + count);
        System.out.println("Минимальное целое число: " + min);
        System.out.println("Максимальное целое число: " + max);
        System.out.println("Сумма целых чисел: " + sum);
        System.out.println("Среднее арифметическое целых чисел: " + average);
    }
}
