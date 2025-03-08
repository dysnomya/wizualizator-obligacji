package com.github.dysnomya.wizualizatorobligacji.controllers;

import com.github.dysnomya.wizualizatorobligacji.database.BondDAO;
import com.github.dysnomya.wizualizatorobligacji.model.Bond;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

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
    private CategoryAxis xAxis;

    @FXML
    private ComboBox investmentDropdown;

    @FXML
    private TextField bondCount;

    @FXML
    private DatePicker bondDate;

    @FXML
    public void initialize() {
        initDropdown();


//        xAxis.setTickLabelFormatter();
    }

    @FXML
    public void initDropdown() {
        investmentDropdown.getItems().setAll(BondDAO.getBonds("TOS"));
    }

    @FXML
    public void setBondDate() {
        Bond bond = (Bond) investmentDropdown.getValue();
        bondDate.setValue(LocalDate.of(bond.getYear(), bond.getMonth(), 1));
    }

    @FXML
    public void generateChart() {
        lineChart.getData().clear();

        if (newInvestment.isExpanded()) {
            lineChart.getData().add(createNewSeries(Double.parseDouble(amountField.getText()), 0, LocalDate.now()));
        } else if (alreadyInvested.isExpanded()) {
            if (this.bondCount.getText().isEmpty()) {
                return;
            }

            Bond bond = (Bond) this.investmentDropdown.getValue();
            double bondCount = Double.parseDouble(this.bondCount.getText());
            double interestRate = bond.getInterestRate();
            int days = (int) ChronoUnit.DAYS.between(LocalDate.now(), bondDate.getValue());
            days = Math.abs(days);
            System.out.println(days);

            XYChart.Series<String, Double> series = createNewSeries(100.0 * bondCount, interestRate, bondDate.getValue());
            series.setName(bond.getId());

            lineChart.getData().add(series);
        } else {
            System.out.println("Wrong data!!!");
        }
    }

    private XYChart.Series<String, Double> createNewSeries(double base, double interestValue, LocalDate startingDate) {
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        for (int i = 0; i < 365; i++) {
            double value = base + ((double) i / 365) * (interestValue / 100) * base;
            series.getData().add(new XYChart.Data<>(startingDate.toString(), value));

            startingDate = startingDate.plusDays(1);
        }
        return series;
    }

}

