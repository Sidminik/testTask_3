package ru.cft.shift.core.statistics.arrays;

public abstract class ArrayStats {
    protected final int count;

    protected ArrayStats(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public abstract void printFullStatistics();
    public abstract void printShortStatistics();
}
