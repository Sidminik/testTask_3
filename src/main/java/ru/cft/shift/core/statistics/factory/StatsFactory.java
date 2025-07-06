package ru.cft.shift.core.statistics.factory;

import ru.cft.shift.core.statistics.arrays.IntegerArrayStats;
import ru.cft.shift.core.statistics.arrays.DoubleArrayStats;
import ru.cft.shift.core.statistics.arrays.StringArrayStats;

import java.util.List;
import java.math.BigInteger;

public class StatsFactory {
    public static IntegerArrayStats createIntegerStats(List<BigInteger> integerArray) {
        return new IntegerArrayStats(integerArray);
    }

    public static DoubleArrayStats createDoubleStats(List<Double> doubleArray) {
        return new DoubleArrayStats(doubleArray);
    }

    public static StringArrayStats createStringStats(List<String> stringArray) {
        return new StringArrayStats(stringArray);
    }
}
