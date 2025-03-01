package com.github.dysnomya.wizualizatorobligacji;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainLayoutController {

    @FXML
    private Pane contentPane;

    private Parent appView;
    private Parent databaseView;

    @FXML
    public void initialize() {
        try {
            appView = FXMLLoader.load(getClass().getResource("app-view.fxml"));
            databaseView = FXMLLoader.load(getClass().getResource("database-view.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadChartView();
    }

    @FXML
    public void loadChartView() {
        loadView(appView);
    }

    @FXML
    public void loadDatabaseView() {
        loadView(databaseView);
    }

    private void loadView(Parent view) {
        contentPane.getChildren().setAll(view);
    }
}
