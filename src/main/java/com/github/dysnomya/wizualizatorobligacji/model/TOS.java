package com.github.dysnomya.wizualizatorobligacji.model;


import javafx.scene.chart.XYChart;
import org.bson.Document;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TOS extends Bond {

    private double interestRate;

    public TOS(String id, double earlyRedemptionPrice, double interestRate) {
        super(id, earlyRedemptionPrice, 365 * 3);
        this.interestRate = interestRate;
    }

    public double[] getInterestRates() {
        return new double[]{interestRate, interestRate, interestRate};
    }

    public int getMonth() {
        return Integer.parseInt(super.getId().substring(3, 5));
    }

    public int getYear() {
        return Integer.parseInt(super.getId().substring(5, 7)) + 1997;
    }

    public XYChart.Series<Long, Double> createNewSeries(int count, LocalDate startDate) {
        XYChart.Series<Long, Double> series = new XYChart.Series<>();

        long minDate = ChronoUnit.DAYS.between(LocalDate.of(1970, 1, 1), startDate);
        long maxDate = ChronoUnit.DAYS.between(LocalDate.of(1970, 1, 1), startDate.plusDays(super.getTime()));
        long range = maxDate - minDate;

        double base = count * 100.0;
        double value = count * 100.0;
        for (int i = 0; i < range; i++) {

            value = value + (interestRate / 365.0) * base;
            series.getData().add(new XYChart.Data<>(minDate + i, value));

        }
        return series;
    }



    @Override
    public String toString() {
        return super.toString() +  ", " + (double) Math.round(interestRate * 10000) / 100 + "%";
    }

    @Override
    public Document toDocument() {
        return super.toDocument().append("interestRate", interestRate);
    }

}
