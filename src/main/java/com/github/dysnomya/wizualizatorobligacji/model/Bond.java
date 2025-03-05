package com.github.dysnomya.wizualizatorobligacji.model;

import org.bson.Document;

import java.time.LocalDate;


public abstract class Bond {
    private String id;
    private double earlyRedemptionPrice;

    public Bond(String id, double earlyRedemptionPrice) {
        this.id = id;
        this.earlyRedemptionPrice = earlyRedemptionPrice;
    }

    public String getId() {
        return id;
    }

    public int getMonth() {
        return Integer.parseInt(id.substring(3, 5));
    }

    public int getYear() {
        if (id.startsWith("TOS")) {
            return Integer.parseInt(id.substring(5, 7)) + 1997;
        }

        return 0;
    }

    public Document toDocument() {
        return new Document("id", id)
                .append("earlyRedemptionPrice", earlyRedemptionPrice);
    }

    public abstract double getInterestRate();

    @Override
    public String toString() {
        return id;
    }
}
