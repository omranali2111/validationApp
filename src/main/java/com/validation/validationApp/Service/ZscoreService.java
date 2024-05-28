package com.validation.validationApp.Service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ZscoreService {

    public List<Double> calculateZScores(List<Double> numbers) {
        List<Double> zScores = new ArrayList<>();
        if (numbers == null || numbers.isEmpty()) {
            return zScores; // No data to process
        }

        double mean = calculateMean(numbers);
        double stdDev = calculateStandardDeviation(numbers, mean);

        for (Double number : numbers) {
            double zScore = (number - mean) / stdDev;
            zScores.add(zScore);
        }

        return zScores;
    }

    public List<Double> findOutliers(List<Double> numbers, double threshold) {
        List<Double> outliers = new ArrayList<>();
        List<Double> zScores = calculateZScores(numbers);

        for (int i = 0; i < zScores.size(); i++) {
            if (Math.abs(zScores.get(i)) > threshold) {
                outliers.add(numbers.get(i));
            }
        }

        return outliers;
    }

    private double calculateMean(List<Double> numbers) {
        double sum = 0;
        for (Double number : numbers) {
            sum += number;
        }
        return sum / numbers.size();
    }

    private double calculateStandardDeviation(List<Double> numbers, double mean) {
        double sum = 0;
        for (Double number : numbers) {
            sum += Math.pow(number - mean, 2);
        }
        return Math.sqrt(sum / numbers.size());
    }
}
