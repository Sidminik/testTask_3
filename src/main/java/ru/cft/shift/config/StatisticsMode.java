package ru.cft.shift.config;

public enum StatisticsMode {
    SHORT, FULL, NONE;

    public static StatisticsMode enumFromString(String value) {
        if ("-s".equals(value)) {
            return SHORT;
        } else if ("-f".equals(value)) {
            return FULL;
        } else {
            return NONE;
        }
    }
}
