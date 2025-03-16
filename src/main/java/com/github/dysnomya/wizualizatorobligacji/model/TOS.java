package com.github.dysnomya.wizualizatorobligacji.model;


import org.bson.Document;

import java.time.LocalDate;

public class TOS extends Bond {

    private double interestRate;

    public TOS(String id, double earlyRedemptionPrice, double interestRate) {
        super(id, earlyRedemptionPrice, 365 * 3);
        this.interestRate = interestRate;
    }

    public int getMonth() {
        return Integer.parseInt(super.getId().substring(3, 5));
    }

    public int getYear() {
        return Integer.parseInt(super.getId().substring(5, 7)) + 1997;
    }


    public Document toDocument() {
        return super.toDocument().append("interestRate", interestRate);
    }

    public double[] getInterestRates() {
        return new double[]{interestRate, interestRate, interestRate};
    }

    @Override
    public String toString() {
        return super.toString() +  ", " + (double) Math.round(interestRate * 10000) / 100 + "%";
    }
}
