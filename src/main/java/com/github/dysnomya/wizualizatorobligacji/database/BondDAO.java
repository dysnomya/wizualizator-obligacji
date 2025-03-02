package com.github.dysnomya.wizualizatorobligacji.database;

import com.github.dysnomya.wizualizatorobligacji.model.Bond;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import org.bson.Document;
import org.bson.conversions.Bson;

public class BondDAO {
    private static final MongoDatabase database = MongoDBConnector.getDatabase();
    private static final MongoCollection<Document> collection = database.getCollection("Obligacje");

    public static void addBond(Bond bond) {
        Bson filter = Filters.eq("id", bond.getId());
        ReplaceOptions options = new ReplaceOptions().upsert(true);
        collection.replaceOne(filter, bond.toDocument(), options);
    }


}
