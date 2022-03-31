module com.project.sorryapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.project.sorryapp to javafx.fxml;
    exports com.project.sorryapp;
}