package com.github.dysnomya.wizualizatorobligacji;

import com.github.dysnomya.wizualizatorobligacji.database.BondDAO;
import com.github.dysnomya.wizualizatorobligacji.model.Bond;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
    private ComboBox investmentDropdown;

    @FXML
    private TextField bondCount;

    @FXML
    private DatePicker bondDate;

    @FXML
    public void initialize() {
        initDropdown();
    }

    @FXML
    public void initDropdown() {
        investmentDropdown.getItems().setAll(BondDAO.getBonds("TOS"));
    }

    @FXML
    public void generateChart() {
        lineChart.getData().clear();

        if (newInvestment.isExpanded()) {
            lineChart.getData().add(createNewSeries(Double.parseDouble(amountField.getText()), 0, 0));
        } else if (alreadyInvested.isExpanded()) {
            double bondCount = Double.parseDouble(this.bondCount.getText());
            double interestRate = ((Bond) this.investmentDropdown.getValue()).getInterestRate();
            int days = (int) ChronoUnit.DAYS.between(LocalDate.now(), bondDate.getValue());
            days = Math.abs(days);

            lineChart.getData().add(createNewSeries(100.0 * bondCount, interestRate, days));
        } else {
            System.out.println("Wrong data!!!");
        }

//        lineChart.getData().add(createNewSeries(Double.parseDouble(amountField.getText()), 5.95));
    }

    private XYChart.Series<Integer, Double> createNewSeries(double base, double interestValue, int startingDay) {
        XYChart.Series<Integer, Double> series = new XYChart.Series<>();
        series.setName("Wartość obligacji w czasie");

        for (int i = startingDay; i < 365; i++) {
            series.getData().add(new XYChart.Data<>(i, base + ((double) i / 365) * (interestValue / 100) * base));
        }
        return series;
    }

}

