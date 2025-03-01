package com.github.dysnomya.wizualizatorobligacji;

import com.github.dysnomya.wizualizatorobligacji.database.BondDAO;
import com.github.dysnomya.wizualizatorobligacji.model.Bond;
import com.github.dysnomya.wizualizatorobligacji.model.TOS;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DatabaseViewController {
    @FXML
    private TextField idField;

    @FXML
    private TextField earlyRedemptionCostField;

    @FXML
    private TextField interestRateField;

    @FXML
    private void handleSaveBond() {
        String id = idField.getText();
        double earlyRedemptionCost = Double.parseDouble(earlyRedemptionCostField.getText());
        double interestRate = Double.parseDouble(interestRateField.getText());

        Bond bond = new TOS(id, earlyRedemptionCost, interestRate);
        BondDAO.addBond(bond);
    }
}
