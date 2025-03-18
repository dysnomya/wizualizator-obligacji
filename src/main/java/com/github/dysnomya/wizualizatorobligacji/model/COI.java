package com.github.dysnomya.wizualizatorobligacji.model;

import javafx.scene.chart.XYChart;
import org.bson.Document;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.Collectors;

public class COI extends Bond {

    private double[] interestRates;

    public COI(String id, double earlyRedemptionPrice, double[] interestRates) {
        super(id, earlyRedemptionPrice, 365 * 4);
        this.interestRates = interestRates;
    }

    public double[] getInterestRates() {
        return interestRates;
    }

    @Override
    public int getMonth() {
        return Integer.parseInt(super.getId().substring(3, 5));
    }

    @Override
    public int getYear() {
        return Integer.parseInt(super.getId().substring(5, 7)) + 1996;
    }

    @Override
    public XYChart.Series<Long, Double> createNewSeries(int count, LocalDate startDate) {
        XYChart.Series<Long, Double> series = new XYChart.Series<>();

        long minDate = ChronoUnit.DAYS.between(LocalDate.of(1970, 1, 1), startDate);
        long maxDate = ChronoUnit.DAYS.between(LocalDate.of(1970, 1, 1), startDate.plusDays(super.getTime()));
        long range = maxDate - minDate;

        double base = count * 100.0;
        double value = count * 100.0;
        for (int i = 0; i < range; i++) {

            value = value + (interestRates[i/365] / 365.0) * base;
            series.getData().add(new XYChart.Data<>(minDate + i, value));

        }
        return series;
    }

    @Override
    public String toString() {
        return super.toString() +  ", " + (double) Math.round(interestRates[0] * 10000) / 100 + "%";
    }

    @Override
    public Document toDocument() {
        String interestRatesString = Arrays.stream(interestRates)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(";"));
        return super.toDocument().append("interestRate", interestRatesString);
    }

}
