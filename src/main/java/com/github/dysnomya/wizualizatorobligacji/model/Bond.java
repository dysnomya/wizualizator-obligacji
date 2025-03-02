package com.github.dysnomya.wizualizatorobligacji.model;

import org.bson.Document;

import java.time.LocalDate;


public abstract class Bond {
    private String id;
    private LocalDate issueFrom;
    private LocalDate issueTo;

    private double earlyRedemptionPrice;

    public Bond(String id, double earlyRedemptionPrice) {
        int year = 1997 + Integer.parseInt(id.substring(5, 7));
        int month = Integer.parseInt(id.substring(3, 5));

        this.id = id;
        this.issueFrom = LocalDate.of(year, month, 1);
        this.issueTo = LocalDate.of(year, month, this.issueFrom.lengthOfMonth());
        this.earlyRedemptionPrice = earlyRedemptionPrice;
    }

    public String getId() {
        return id;
    }

    public Document toDocument() {
        return new Document("id", id)
                .append("earlyRedemptionPrice", earlyRedemptionPrice)
                .append("issueFrom", issueFrom)
                .append("issueTo", issueTo);
    }

    @Override
    public String toString() {
        return id +
                " [" + issueFrom.getMonth() + " " + issueFrom.getYear() +
                "], cena przedterminowego wykupu: " + earlyRedemptionPrice;
    }
}
