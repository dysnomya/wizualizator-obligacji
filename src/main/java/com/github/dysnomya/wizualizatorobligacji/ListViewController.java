package com.github.dysnomya.wizualizatorobligacji;

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
    private         List<Bond>              bondList     = new ArrayList<>(5);
    private         ObservableList<Bond>  names = FXCollections.observableArrayList();

    public void setListView() {
        bondList.add(new TOS("TOS1212", 1, 2));

        names.setAll(bondList);
        tosBonds.setItems(names);
    }

    @FXML
    public void initialize() {
        setListView();
    }

}
