package com.github.dysnomya.wizualizatorobligacji;

import com.github.dysnomya.wizualizatorobligacji.database.MongoDBConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WizualizatorObligacji extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MongoDBConnector.connect();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-layout.fxml"));

        Scene scene = new Scene(loader.load(), 1024, 768);
        stage.setTitle("Wizualizator Obligacji");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        MongoDBConnector.close();
    }

    public static void main(String[] args) {
        launch();
    }
}