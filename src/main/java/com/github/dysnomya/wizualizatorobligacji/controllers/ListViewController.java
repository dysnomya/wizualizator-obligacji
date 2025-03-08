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
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONException;
import org.json.JSONObject;

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

    @FXML
    private String loadFromAPI() throws JSONException, IOException, URISyntaxException {
        Workbook workbook = getXMLFile();
        XMLFileReader fileReader = new XMLFileReader(workbook);
        fileReader.readSheet();
        return "abc";
    }

    private Workbook getXMLFile() throws IOException, JSONException, URISyntaxException {
        URL url = new URI("https://api.dane.gov.pl/resources/64629,sprzedaz-obligacji-detalicznych/file").toURL();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();

        InputStream inputStream = con.getInputStream();


        return new HSSFWorkbook(inputStream);
    }
}
