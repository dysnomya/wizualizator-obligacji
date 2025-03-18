package com.github.dysnomya.wizualizatorobligacji.model;

import javafx.scene.chart.XYChart;
import org.bson.Document;

import java.time.LocalDate;


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

    public abstract XYChart.Series<Long, Double> createNewSeries(int count, LocalDate startDate);

    @Override
    public String toString() {
        return id;
    }

    public Document toDocument() {
        return new Document("id", id)
                .append("earlyRedemptionPrice", earlyRedemptionPrice);
    }

}
