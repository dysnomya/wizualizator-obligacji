package com.github.dysnomya.wizualizatorobligacji.database;

import com.github.dysnomya.wizualizatorobligacji.model.Bond;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class BondDAO {
    private static final MongoDatabase database = MongoDBConnector.getDatabase();
    private static final MongoCollection<Document> collection = database.getCollection("Obligacje");

    public static void addBond(Bond bond) {
        collection.insertOne(bond.toDocument());
    }
}
