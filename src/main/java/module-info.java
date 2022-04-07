module com.project.sorryapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires hibernate.core;
    requires hibernate.jpa;
    requires java.naming;
    requires java.sql;

    opens com.project.sorryapp to javafx.fxml;
    exports com.project.sorryapp;
}