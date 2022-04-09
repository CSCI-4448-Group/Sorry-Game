module com.project.sorryapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires java.sql;

    opens com.project.sorryapp to javafx.fxml;
    exports com.project.sorryapp;
}