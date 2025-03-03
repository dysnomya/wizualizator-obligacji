package com.github.dysnomya.wizualizatorobligacji;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WizualizatorObligacjiController {

    // settings
    @FXML
    private TitledPane newInvestment;

    @FXML
    private TitledPane alreadyInvested;

    @FXML
    private TextField amountField;

    @FXML
    private LineChart lineChart;

    @FXML
    public void handleNewInvestment() {
        if (newInvestment.isExpanded()) {

        }
    }

    private XYChart.Series<Integer, Double> createNewSeries(double base, double interestValue) {
        XYChart.Series<Integer, Double> series = new XYChart.Series<>();
        series.setName("Wartość obligacji w czasie");

        for (int i = 0; i < 365; i++) {
            series.getData().add(new XYChart.Data<>(i, base + ((double) i / 365) * (interestValue / 100) * base ));
        }
        return series;
    }


    @FXML
    public void generateChart() {
        lineChart.getData().clear();

        if (newInvestment.isExpanded()) {
            lineChart.getData().add(createNewSeries(Double.parseDouble(amountField.getText()), 0));
        } else if (alreadyInvested.isExpanded()) {

        } else {
            System.out.println("Wrong data!!!");
        }

        System.out.println("start generating...");
        lineChart.getData().add(createNewSeries(Double.parseDouble(amountField.getText()), 5.95));
        System.out.println("finished generating...");
    }
}

