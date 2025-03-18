package com.github.dysnomya.wizualizatorobligacji.controllers;

import com.github.dysnomya.wizualizatorobligacji.database.BondDAO;
import com.github.dysnomya.wizualizatorobligacji.model.Bond;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import org.controlsfx.control.SearchableComboBox;

import java.time.LocalDate;
import java.util.Date;

public class BondViewController {

    @FXML private TitledPane                pane;
    @FXML private SearchableComboBox<Bond>  investmentDropdown;
    @FXML private TextField                 bondCount;
    @FXML private DatePicker                bondDate;


    public Bond getBond() {
        return investmentDropdown.getValue();
    }

    public int getCount() {
        return Integer.parseInt(bondCount.getText());
    }

    public LocalDate getDate() {
        return bondDate.getValue();
    }

    @FXML
    private void initialize() {
        initDropdown();
    }

    @FXML
    private void handleComboBox() {
        Bond bond = investmentDropdown.getValue();
        if (bond != null) {
            setPaneTitle(bond);
            setBondDate(bond);
        }
    }

    @FXML
    private void setPaneTitle(Bond bond) {
        pane.setText(bond.getId());
    }

    @FXML
    private void setBondDate(Bond bond) {
        bondDate.setValue(LocalDate.of(bond.getYear(), bond.getMonth(), 1));
    }

    @FXML
    private void initDropdown() {
        investmentDropdown.getItems().setAll(BondDAO.getBonds("TOS"));
        investmentDropdown.getItems().addAll(BondDAO.getBonds("COI"));
    }
}
