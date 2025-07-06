package ru.cft.shift.core.statistics.arrays;

import java.util.List;

public class StringArrayStats extends ArrayStats {
    private int minLength;
    private int maxLength;

    public StringArrayStats(List<String> stringArray) {
        super(stringArray.size());
        minLength = stringArray.getFirst().length();
        maxLength = stringArray.getFirst().length();

        if (count > 1) {
            for (int i = 1; i < count; i++) {
                if (stringArray.get(i).length() < minLength) {
                    minLength = stringArray.get(i).length();
                }
                if (stringArray.get(i).length() > maxLength) {
                    maxLength = stringArray.get(i).length();
                }
            }
        }
    }

    @Override
    public void printFullStatistics() {
        System.out.println("Количество записанных строк: " + count);
        System.out.println("Минимальная длина строки: " + minLength);
        System.out.println("Максимальная длина строки: " + maxLength);
    }

    @Override
    public void printShortStatistics() {
        System.out.println("Количество записанных строк: " + count);
    }
}
