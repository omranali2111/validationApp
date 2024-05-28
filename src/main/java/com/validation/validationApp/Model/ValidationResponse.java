package com.validation.validationApp.Model;

import java.util.List;

public class ValidationResponse {
    private String validationResult;
    private List<Double> outliers;

    // Getters and setters
    public String getValidationResult() {
        return validationResult;
    }

    public void setValidationResult(String validationResult) {
        this.validationResult = validationResult;
    }

    public List<Double> getOutliers() {
        return outliers;
    }

    public void setOutliers(List<Double> outliers) {
        this.outliers = outliers;
    }
}
