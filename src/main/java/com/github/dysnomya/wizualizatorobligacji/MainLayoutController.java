package com.github.dysnomya.wizualizatorobligacji;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class MainLayoutController {

    @FXML
    private Pane contentPane;

    @FXML
    public void initialize() {
        loadChartView();
    }

    @FXML
    public void loadChartView() {
        loadView("app-view.fxml");
    }

    @FXML
    public void loadDatabaseView() {
        loadView("database-view.fxml");
    }

    private void loadView(String fxmlFile) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxmlFile));
            contentPane.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
