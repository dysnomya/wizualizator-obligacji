module com.github.dysnomya.wizualizatorobligacji {
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires org.json.chargebee;
    requires org.apache.poi.poi;
    requires org.controlsfx.controls;


    opens com.github.dysnomya.wizualizatorobligacji to javafx.fxml;
    exports com.github.dysnomya.wizualizatorobligacji;
    exports com.github.dysnomya.wizualizatorobligacji.database;
    opens com.github.dysnomya.wizualizatorobligacji.database to javafx.fxml;
    exports com.github.dysnomya.wizualizatorobligacji.controllers;
    opens com.github.dysnomya.wizualizatorobligacji.controllers to javafx.fxml;
}