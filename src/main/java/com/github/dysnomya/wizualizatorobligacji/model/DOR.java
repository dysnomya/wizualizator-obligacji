package com.github.dysnomya.wizualizatorobligacji.model;

import org.bson.Document;

import java.time.Month;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DOR extends Bond {

    private final double[] interestRates;

    public DOR(String id, double earlyRedemptionRate, double[] interestRates) {
        super(id, earlyRedemptionRate, 365 * 2);
        this.interestRates = interestRates;
    }

    @Override
    public int getMonth() {
        return Integer.parseInt(super.getId().substring(3, 5));
    }

    @Override
    public int getYear() {
        return Integer.parseInt(super.getId().substring(5, 7)) + 1998;
    }

    @Override
    public double calculateDailyInterestRate(int day) {
        return interestRates[calculateInterestPeriod(day)] / 365;
    }

    private int calculateInterestPeriod(int day) {
        int interestPeriod = 0;
        int month = getMonth();
        while (day >= 0) {
            day -= Month.of(month).maxLength();
            month = ((month + 1) % 12) + 1;
            interestPeriod += 1;
        }

        return interestPeriod;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + interestRates[0];
    }

    public Document toDocument() {
        String interestRatesString = Arrays.stream(interestRates)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(";"));

        return super.toDocument()
                .append("interestRate", interestRatesString);
    }

}
