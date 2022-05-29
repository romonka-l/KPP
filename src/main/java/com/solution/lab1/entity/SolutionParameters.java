package com.solution.lab1.entity;

import java.util.Objects;

public class SolutionParameters {
    private int firstValue;
    private int secondValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolutionParameters that = (SolutionParameters) o;
        return firstValue == that.firstValue && secondValue == that.secondValue && lowerInterval == that.lowerInterval && higherInterval == that.higherInterval;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue, lowerInterval, higherInterval);
    }

    private int lowerInterval;
    private int higherInterval;

    public SolutionParameters(int value1, int value2, int lowerInterval, int interval2) {
        this.firstValue = value1;
        this.secondValue = value2;
        this.lowerInterval = lowerInterval;
        this.higherInterval = interval2;
    }

    public int getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(int secondValue) {
        this.secondValue = secondValue;
    }

    public int getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(int firstValue) {
        this.firstValue = firstValue;
    }

    public int getLowerInterval() {
        return lowerInterval;
    }

    public void setLowerInterval(int lowerInterval) {
        this.lowerInterval = lowerInterval;
    }

    public int getHigherInterval() {
        return higherInterval;
    }

    public void setHigherInterval(int higherInterval) {
        this.higherInterval = higherInterval;
    }
}
