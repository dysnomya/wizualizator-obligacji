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

    @Override
    public double calculateDailyInterestRate(int day) {
        return interestRate / 365;
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
