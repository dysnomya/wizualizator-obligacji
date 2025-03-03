package com.github.dysnomya.wizualizatorobligacji;

import com.github.dysnomya.wizualizatorobligacji.database.BondDAO;
import com.github.dysnomya.wizualizatorobligacji.model.Bond;
import com.github.dysnomya.wizualizatorobligacji.model.TOS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ListViewController {
    @FXML private   ListView                tosBonds;
    private         ObservableList<Bond>  names = FXCollections.observableArrayList();

    public void setListView() {

        List<Bond> bonds = BondDAO.getBonds("TOS");

        names.setAll(bonds);
    }

    @FXML
    public void initialize() {
        tosBonds.setItems(names);
        setListView();
    }

}
