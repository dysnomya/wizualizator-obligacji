package com.github.dysnomya.wizualizatorobligacji;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class WizualizatorObligacjiController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        if (welcomeText.getText().equals("")) {
            welcomeText.setText("Welcome to JavaFX Application!");
        } else {
            welcomeText.setText("");
        }
    }


    // chart
    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    public void initialize() {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Bond Value Over Time");

        series.getData().add(new XYChart.Data<>(1, 100));
        series.getData().add(new XYChart.Data<>(2, 120));
        series.getData().add(new XYChart.Data<>(3, 150));
        series.getData().add(new XYChart.Data<>(4, 130));
        series.getData().add(new XYChart.Data<>(5, 170));

        lineChart.getData().add(series);
    }
}