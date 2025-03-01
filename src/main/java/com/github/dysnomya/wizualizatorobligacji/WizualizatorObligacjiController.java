package com.github.dysnomya.wizualizatorobligacji;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WizualizatorObligacjiController {

    // settings
    @FXML
    private RadioButton newInvestment;

    @FXML
    private HBox newInvestmentOptions;

    @FXML
    private RadioButton alreadyInvested;

    @FXML
    private VBox alreadyInvestedOptions;

    @FXML
    public void handleNewInvestment() {

    }

    @FXML
    public void toggleOptions() {
        newInvestmentOptions.setVisible(newInvestment.isSelected());
        newInvestmentOptions.setManaged(newInvestment.isSelected());
        alreadyInvestedOptions.setVisible(alreadyInvested.isSelected());
        alreadyInvestedOptions.setManaged(alreadyInvested.isSelected());
    }


//    private XYChart.Series<Number, Number> createNewSeries() {
//        XYChart.Series<Number, Number> series = new XYChart.Series<>();
//        series.setName("Wartość obligacji w czasie");
//
//        for (int i = 0; i < timeValue; i++) {
//            series.getData().add(new XYChart.Data<>(i, bondAmountValue * 100));
//        }
//
//        return series;
//    }
}