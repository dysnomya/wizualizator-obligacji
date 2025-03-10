package com.github.dysnomya.wizualizatorobligacji.controllers;

import com.github.dysnomya.wizualizatorobligacji.WizualizatorObligacji;
import com.github.dysnomya.wizualizatorobligacji.database.BondDAO;
import com.github.dysnomya.wizualizatorobligacji.model.Bond;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class WizualizatorObligacjiController {

    // settings
    @FXML
    private TextField amountField;

    @FXML
    private LineChart lineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private Accordion bondAccordion;


    @FXML
    private void addNewBond() throws IOException {
        FXMLLoader loader = new FXMLLoader(WizualizatorObligacji.class.getResource("bond-view.fxml"));
        TitledPane pane = loader.load();
        pane.getProperties().put("loader", loader);
        bondAccordion.getPanes().add(pane);
    }

    @FXML
    public void generateChart() {
        lineChart.getData().clear();

        for (TitledPane pane : bondAccordion.getPanes()) {
            FXMLLoader loader = (FXMLLoader) pane.getProperties().get("loader");

            BondViewController controller = loader.getController();
            Bond bond = controller.getBond();
            int count = controller.getCount();
            LocalDate date = controller.getDate();

            XYChart.Series<String, Double> series = createNewSeries(100.0 * count, bond.getInterestRate(), date);
            series.setName(bond.getId());

            lineChart.getData().add(series);
        }
    }

    private XYChart.Series<String, Double> createNewSeries(double base, double interestValue, LocalDate startingDate) {
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        for (int i = 0; i < 365; i++) {
            double value = base + ((double) i / 365) * (interestValue) * base;
            series.getData().add(new XYChart.Data<>(startingDate.toString(), value));

            startingDate = startingDate.plusDays(1);
        }
        return series;
    }

}

