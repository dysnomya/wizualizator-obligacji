package com.github.dysnomya.wizualizatorobligacji.model;


import org.bson.Document;

import java.time.LocalDate;

public class TOS extends Bond {

    private double interestRate;

    public TOS(String id, double earlyRedemptionPrice, double interestRate) {
        super(id, earlyRedemptionPrice);
        this.interestRate = interestRate;
    }

    public Document toDocument() {
        return super.toDocument().append("interestRate", interestRate);
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public String toString() {
        return super.toString() +  ", oprocentowanie: " + interestRate + "%";
    }
}
