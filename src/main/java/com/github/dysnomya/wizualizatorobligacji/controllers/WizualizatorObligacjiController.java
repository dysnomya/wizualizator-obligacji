package com.github.dysnomya.wizualizatorobligacji.controllers;

import com.github.dysnomya.wizualizatorobligacji.WizualizatorObligacji;
import com.github.dysnomya.wizualizatorobligacji.database.BondDAO;
import com.github.dysnomya.wizualizatorobligacji.model.Bond;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

public class WizualizatorObligacjiController {

    // settings
    @FXML
    private TextField amountField;

    @FXML
    private LineChart<Long, Double> lineChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private Accordion bondAccordion;

    @FXML
    private void initialize() {
        xAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(xAxis) {
            @Override
            public String toString(Number number) {
                LocalDate date = LocalDate.of(1970, 1, 1).plusDays(Math.round((double) number));
                return date.toString();
            }
        });

    }

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

            XYChart.Series<Long, Double> series = bond.createNewSeries(count, date);
            series.setName(bond.getId());

            lineChart.getData().add(series);
        }
    }

}

