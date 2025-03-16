package com.github.dysnomya.wizualizatorobligacji.model;

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

    public int getMonth() {
        return Integer.parseInt(id.substring(3, 5));
    }

    public int getYear() {
        if (id.startsWith("TOS")) {
            return Integer.parseInt(id.substring(5, 7)) + 1997;
        }

        return 0;
    }

    public int getTime() {
        return time;
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
