package com.github.dysnomya.wizualizatorobligacji.database;

import com.github.dysnomya.wizualizatorobligacji.model.Bond;
import com.github.dysnomya.wizualizatorobligacji.model.COI;
import com.github.dysnomya.wizualizatorobligacji.model.TOS;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BondDAO {
    private static final MongoDatabase database = MongoDBConnector.getDatabase();
    private static final MongoCollection<Document> collection = database.getCollection("Obligacje");

    public static void addBond(Bond bond) {
        Bson filter = Filters.eq("id", bond.getId());
        ReplaceOptions options = new ReplaceOptions().upsert(true);
        collection.replaceOne(filter, bond.toDocument(), options);
    }

    public static List<Bond> getBonds(String type) {
        Pattern regex = Pattern.compile("^" + type, Pattern.CASE_INSENSITIVE);
        Bson filter = Filters.eq("id", regex);

        FindIterable<Document> docs = collection.find(filter);

        ArrayList<Bond> bonds = new ArrayList<>();
        for (Document doc : docs) {
            String id = doc.getString("id");
            double earlyRedemptionPrice = doc.getDouble("earlyRedemptionPrice");

            if (id.startsWith("TOS")) {
                double interestRate = doc.getDouble("interestRate");
                bonds.add(new TOS(id, earlyRedemptionPrice, interestRate));
            } else if (id.startsWith("COI")) {
                double[] interestRate = Arrays.stream(doc.getString("interestRate").split(";"))
                        .mapToDouble(Double::parseDouble)
                        .toArray();
                bonds.add(new COI(id, earlyRedemptionPrice, interestRate));
            }
        }

        return bonds;
    }
}
