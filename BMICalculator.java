package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BMICalculator extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BMI Calculator");

        Label weightLabel = new Label("Weight (kg):");
        TextField weightField = new TextField();

        Label heightLabel = new Label("Height (cm):");
        TextField heightField = new TextField();

        Label bmiLabel = new Label();

        Button calculateButton = new Button("Calculate");
        calculateButton.setOnAction(e -> {
            try {
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText());
                double bmi = calculateBMI(weight, height);
                displayBMI(bmi);

                bmiLabel.setText(String.format("BMI: %.2f", bmi));
            } catch (NumberFormatException ex) {
                showErrorAlert("Invalid input. Please enter valid numeric values.");
            }
        });

       // Label resultLabel = new Label();

        VBox vbox = new VBox(weightLabel, weightField, heightLabel, heightField, calculateButton, bmiLabel);
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double calculateBMI(double weight, double height) {
        return (weight / Math.pow(height / 100, 2));
    }

    private void displayBMI(double bmi) {
        String message;
        if (bmi < 18.5) {
            message = "You are underweight.";
        } else if (bmi <= 24.9) {
            message = "You have a normal weight. Well done!";
        } else if (bmi <= 29.9) {
            message = "You are overweight.";
        } else {
            message = "You are obese.";
        }
        showAlert("BMI Result", null, message);
    }

    private void showErrorAlert(String errorMessage) {
        showAlert("Error", null, errorMessage, Alert.AlertType.ERROR);
    }

    private void showAlert(String title, String header, String content) {
        showAlert(title, header, content, Alert.AlertType.INFORMATION);
    }

    private void showAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
