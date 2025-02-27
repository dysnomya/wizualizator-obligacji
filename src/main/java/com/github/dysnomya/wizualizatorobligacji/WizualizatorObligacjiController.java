package com.github.dysnomya.wizualizatorobligacji;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WizualizatorObligacjiController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}