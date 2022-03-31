module com.example.homeapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.homeapp to javafx.fxml;
    exports com.example.homeapp;
}