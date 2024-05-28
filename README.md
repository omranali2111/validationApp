# ValidationApp

## Overview

ValidationApp is a Spring Boot application designed to validate numerical inputs through a series of checks, including z-score outlier detection and rate of change validation. 
This application ensures that the data entered adheres to specified validation rules, making it suitable for scenarios where data integrity and quality are critical.

## Features

- **Z-Score Outlier Detection**: Utilizes z-score to identify and flag outliers within the dataset.
- **Rate of Change Validation**: Calculates the percentage change between the new input and the last value in the dataset, validating it against a predefined threshold.
- **Trendline Calculation**: Computes trendline values and averages for the dataset to assess the data trend.
- **CORS Support**: Configured to handle cross-origin requests, making it accessible from different origins.
- **RESTful API**: Exposes a RESTful API for easy integration and usage.

## Technologies Used

- **Spring Boot**: Provides the framework for building the application.
- **Maven**: Manages project dependencies.
- **Java**: The programming language used for the application logic.
- **Apache Commons Math**: For statistical calculations (z-score).

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/ValidationApp.git
   cd ValidationApp
