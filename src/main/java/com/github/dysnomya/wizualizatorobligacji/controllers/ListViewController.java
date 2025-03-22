package com.github.dysnomya.wizualizatorobligacji.controllers;

import com.github.dysnomya.wizualizatorobligacji.database.BondDAO;
import com.github.dysnomya.wizualizatorobligacji.database.XMLFileReader;
import com.github.dysnomya.wizualizatorobligacji.model.Bond;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.*;
import java.net.*;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONException;

public class ListViewController {
    @FXML private   ListView<Bond>          tosBonds;
    @FXML private   ListView<Bond>          coiBonds;
    @FXML private   ListView<Bond>          dorBonds;
    private         ObservableList<Bond>    tosNames = FXCollections.observableArrayList();
    private         ObservableList<Bond>    coiNames = FXCollections.observableArrayList();
    private         ObservableList<Bond>    dorNames = FXCollections.observableArrayList();

    public void setListView() {
        List<Bond> bonds = BondDAO.getBonds("TOS");
        tosNames.setAll(bonds);

        bonds = BondDAO.getBonds("COI");
        coiNames.setAll(bonds);

        bonds = BondDAO.getBonds("DOR");
        dorNames.setAll(bonds);
    }

    @FXML
    public void initialize() {
        tosBonds.setItems(tosNames);
        coiBonds.setItems(coiNames);
        dorBonds.setItems(dorNames);
        setListView();
    }

    @FXML
    private void loadFromAPI() throws JSONException, IOException, URISyntaxException {
        Workbook workbook = getXMLFile();
        XMLFileReader fileReader = new XMLFileReader(workbook);
        fileReader.readSheet();
    }

    private Workbook getXMLFile() throws IOException, JSONException, URISyntaxException {
        URL url = new URI("https://api.dane.gov.pl/resources/65327,sprzedaz-obligacji-detalicznych/file").toURL();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();

        InputStream inputStream = con.getInputStream();


        return new HSSFWorkbook(inputStream);
    }
}
