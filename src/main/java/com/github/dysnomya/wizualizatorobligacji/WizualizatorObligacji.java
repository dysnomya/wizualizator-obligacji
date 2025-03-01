package com.github.dysnomya.wizualizatorobligacji;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class WizualizatorObligacji extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-layout.fxml"));

        Scene scene = new Scene(loader.load(), 1024, 768);
        stage.setTitle("Wizualizator Obligacji");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}