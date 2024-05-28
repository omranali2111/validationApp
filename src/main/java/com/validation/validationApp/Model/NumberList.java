package com.validation.validationApp.Model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class NumberList {
    private List<Double> numbers;
    public NumberList() {
        this.numbers = new ArrayList<>(Arrays.asList(
                1777981.0, 1753353.0, 1654988.0, 1612313.0, 1604574.0, 1613047.0, 1730783.0, 1805695.0, 1845615.0,
                1835014.0, 1806507.0, 1776555.0, 1782607.0, 1770474.0, 1771787.0, 1750819.0, 1750697.0, 1748305.0,
                1739065.0, 1744358.0, 1762849.0, 1775108.0, 1759233.0, 1740038.0, 1860415.0, 1955193.0, 1961463.0,
                1953026.0
        ));
    }

    public List<Double> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Double> numbers) {
        this.numbers = numbers;
    }

    public void addNumber(Double number) {
        this.numbers.add(number);
    }
}
