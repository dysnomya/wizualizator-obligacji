module com.github.dysnomya.wizualizatorobligacji {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;


    opens com.github.dysnomya.wizualizatorobligacji to javafx.fxml;
    exports com.github.dysnomya.wizualizatorobligacji;
    exports com.github.dysnomya.wizualizatorobligacji.database;
    opens com.github.dysnomya.wizualizatorobligacji.database to javafx.fxml;
}