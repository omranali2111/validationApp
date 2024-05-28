package com.validation.validationApp.Service;

import com.validation.validationApp.Model.ValidationResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class NumberService {

    public List<Double> calculatePercentageChanges(List<Double> numbers) {
        List<Double> percentageChanges = new ArrayList<>();

        if (numbers == null || numbers.size() < 2) {
            return percentageChanges; // Not enough data to calculate changes
        }

        for (int i = 1; i < numbers.size(); i++) {
            double previousNumber = numbers.get(i - 1);
            double currentNumber = numbers.get(i);

            double percentageChange = Math.abs((currentNumber - previousNumber) / previousNumber) * 100;
            percentageChanges.add(percentageChange);
        }

        return percentageChanges;
    }

    public List<Double> calculateTrendline(List<Double> percentageChanges) {
        List<Double> trendlineValues = new ArrayList<>();
        int n = percentageChanges.size();

        if (n < 2) {
            return trendlineValues; // Not enough data to calculate trendline
        }

        double sumX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumXX = 0;

        for (int i = 0; i < n; i++) {
            sumX += i;
            sumY += percentageChanges.get(i);
            sumXY += i * percentageChanges.get(i);
            sumXX += i * i;
        }

        double slope = (n * sumXY - sumX * sumY) / (n * sumXX - sumX * sumX);
        double intercept = (sumY - slope * sumX) / n;

        for (int i = 0; i < n; i++) {
            double trendlineValue = slope * i + intercept;
            trendlineValues.add(trendlineValue);
        }

        return trendlineValues;
    }

    public double calculateAverageTrendlinePercentage(List<Double> numbers) {
        List<Double> percentageChanges = calculatePercentageChanges(numbers);
        List<Double> trendlineValues = calculateTrendline(percentageChanges);

        if (trendlineValues.isEmpty()) {
            return 0.0; // Not enough data to calculate average trendline percentage
        }

        double total = 0;
        for (Double value : trendlineValues) {
            total += value;
        }

        double averageTrendlineValue = total / trendlineValues.size();
        return averageTrendlineValue;
    }

    public double calculatePercentageChangeWithLastNumber(List<Double> numbers, double inputValue) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("The numbers list is empty or null.");
        }

        double lastNumber = numbers.get(numbers.size() - 1);
        return (Math.abs(inputValue - lastNumber) / lastNumber) * 100;
    }

}
