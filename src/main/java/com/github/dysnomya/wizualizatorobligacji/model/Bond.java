package com.github.dysnomya.wizualizatorobligacji.model;

import javafx.scene.chart.XYChart;
import org.bson.Document;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public abstract class Bond {
    private String id;
    private double earlyRedemptionPrice;
    private int time;

    public Bond(String id, double earlyRedemptionPrice, int time) {
        this.id = id;
        this.earlyRedemptionPrice = earlyRedemptionPrice;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public abstract int getMonth();
    public abstract int getYear();

    public int getTime() {
        return time;
    }

    public abstract double[] getInterestRates();

    public abstract double calculateDailyInterestRate(int day);


    public XYChart.Series<Long, Double> createNewSeries(int count, LocalDate startDate) {
        XYChart.Series<Long, Double> series = new XYChart.Series<>();

        long minDate = ChronoUnit.DAYS.between(LocalDate.of(1970, 1, 1), startDate);
        long maxDate = ChronoUnit.DAYS.between(LocalDate.of(1970, 1, 1), startDate.plusDays(time));
        long range = maxDate - minDate;

        double base = count * 100.0;
        double value = count * 100.0;
        for (int i = 0; i < range; i++) {

            value = value + this.calculateDailyInterestRate(i) * base;
            series.getData().add(new XYChart.Data<>(minDate + i, value));

        }
        return series;
    }

    @Override
    public String toString() {
        return id;
    }

    public Document toDocument() {
        return new Document("id", id)
                .append("earlyRedemptionPrice", earlyRedemptionPrice);
    }

}
