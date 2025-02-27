module com.github.dysnomya.wizualizatorobligacji {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.github.dysnomya.wizualizatorobligacji to javafx.fxml;
    exports com.github.dysnomya.wizualizatorobligacji;
}