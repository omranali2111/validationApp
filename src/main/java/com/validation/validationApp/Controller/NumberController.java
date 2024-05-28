package com.validation.validationApp.Controller;

import com.validation.validationApp.Model.NumberList;
import com.validation.validationApp.Model.ValidationResponse;
import com.validation.validationApp.Service.NumberService;
import com.validation.validationApp.Service.ZscoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/numbers")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NumberController {

    private final NumberList numberList;
    private final ZscoreService zscoreService;
    private final NumberService numberService;
    @Autowired
    public NumberController(NumberList numberList, ZscoreService zscoreService,NumberService numberService) {
        this.numberList = numberList;
        this.zscoreService = zscoreService;
        this.numberService=numberService;
    }


    @PostMapping
    public ValidationResponse addAndValidateNumber(@RequestParam Double number) {
        // Create a temporary list including the existing numbers and the new number
        List<Double> tempList = new ArrayList<>(numberList.getNumbers());
        tempList.add(number);

        // Calculate z-scores for the temporary list
        List<Double> zScores = zscoreService.calculateZScores(tempList);
        double Threshold = 2.5;

        // Get the z-score of the new number (last number in tempList)
        double zScore = zScores.get(zScores.size() - 1);

        // Validation response
        ValidationResponse validationResult = new ValidationResponse();

        // Check if the new number is an outlier based on z-score
        if (Math.abs(zScore) > Threshold) {
            validationResult.setValidationResult("Error: Number is an outlier.");
        } else {
            // Calculate average trendline percentage
            double averageTrendlinePercentage = numberService.calculateAverageTrendlinePercentage(numberList.getNumbers());

            // Calculate percentage change with the last number
            double percentageChangeWithLastNumber = numberService.calculatePercentageChangeWithLastNumber(numberList.getNumbers(), number);



            // Check if the percentage change is within the acceptable range
            if (percentageChangeWithLastNumber > averageTrendlinePercentage + 2) {
                validationResult.setValidationResult("Error: Percentage change exceeds the acceptable range.");
            } else {
              //  numberList.addNumber(number); // Add number to the original list if validated
                validationResult.setValidationResult("Validated");
            }
        }

        // Identify all outliers in the original list (without the newly added number)
        List<Double> outliers = zscoreService.findOutliers(numberList.getNumbers(), Threshold);
        validationResult.setOutliers(outliers);

        return validationResult;
    }
}
