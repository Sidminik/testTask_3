package ru.cft.shift.core.data;

import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

public class WritableArraysDTO {
    private final List<BigInteger> integers;
    private final List<Double> doubles;
    private final List<String> strings;

    public WritableArraysDTO() {
        this.integers = new ArrayList<>();
        this.doubles = new ArrayList<>();
        this.strings = new ArrayList<>();
    }

    public List<BigInteger> getIntegersArray() {
        return integers;
    }

    public List<Double> getDoublesArray() {
        return doubles;
    }

    public List<String> getStringsArray() {
        return strings;
    }

    public void addInteger(BigInteger integerElement) {
       this.integers.add(integerElement);
    }

    public void addDouble(Double doubleElement) {
        this.doubles.add(doubleElement);
    }

    public void addString(String stringElement) {
        this.strings.add(stringElement);
    }
}
