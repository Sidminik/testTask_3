package ru.cft.shift.core.statistics.services;

import ru.cft.shift.config.StatisticsMode;
import ru.cft.shift.core.data.WritableArraysDTO;
import ru.cft.shift.core.statistics.arrays.DoubleArrayStats;
import ru.cft.shift.core.statistics.arrays.IntegerArrayStats;
import ru.cft.shift.core.statistics.arrays.StringArrayStats;
import ru.cft.shift.core.statistics.factory.StatsFactory;

public class StatisticProcessingService {
    private final StatisticsMode statisticMode;
    private final IntegerArrayStats integerStats;
    private final DoubleArrayStats doubleStats;
    private final StringArrayStats stringStats;

    public StatisticProcessingService(StatisticsMode statisticMode, WritableArraysDTO data) {
        this.statisticMode = statisticMode;
        this.integerStats = StatsFactory.createIntegerStats(data.getIntegersArray());
        this.doubleStats = StatsFactory.createDoubleStats(data.getDoublesArray());
        this.stringStats = StatsFactory.createStringStats(data.getStringsArray());
    }

    public void printStatistics() {
        if (statisticMode == StatisticsMode.SHORT) {
            printShortStatistics();
        } else if (statisticMode == StatisticsMode.FULL) {
            printFullStatistics();
        }
    }

    public void printShortStatistics() {
        integerStats.printShortStatistics();
        doubleStats.printShortStatistics();
        stringStats.printShortStatistics();
    }

    public void printFullStatistics() {
        if (integerStats.getCount() > 0) {
            integerStats.printFullStatistics();
        }
        if (doubleStats.getCount() > 0) {
            doubleStats.printFullStatistics();
        }
        if (stringStats.getCount() > 0) {
            stringStats.printFullStatistics();
        }
    }
}
