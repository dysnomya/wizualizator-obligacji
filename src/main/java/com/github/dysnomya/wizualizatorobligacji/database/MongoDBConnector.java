package com.github.dysnomya.wizualizatorobligacji.database;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnector {
    private static final String CONNECTION_STRING = "mongodb+srv://Cluster52018:WEZfU3ZsZVhT@cluster52018.ihkod.mongodb.net/?retryWrites=true&w=majority&appName=Cluster52018";
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static void connect() {
        try {
            mongoClient = MongoClients.create(CONNECTION_STRING);
            database = mongoClient.getDatabase("wizualizator_obligacji");
            System.out.println("Connected to MongoDB!");
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Closed connection to MongoDB!");
        }
    }
}
