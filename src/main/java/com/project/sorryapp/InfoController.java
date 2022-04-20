package com.project.sorryapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InfoController implements SceneLoader, Initializable {
    @FXML
    public void on_home_clicked(ActionEvent event){
        load_scene_from_event("home-view.fxml", event);
    }

    @FXML
    private TableView<LeaderboardModel> leaderboard_table;
    @FXML
    private TableColumn<LeaderboardModel, String> col_gameid;
    @FXML
    private TableColumn<LeaderboardModel, String> col_name;
    @FXML
    private TableColumn<LeaderboardModel, String> col_moved;
    @FXML
    private TableColumn<LeaderboardModel, String> col_sorries;
    @FXML
    private TableColumn<LeaderboardModel, String> col_started;
    @FXML
    private TableColumn<LeaderboardModel, String> col_home;

    ObservableList<LeaderboardModel> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (GameController.getTrackAndUseDB()) {
            DbRunner db = new DbRunner();
            Connection con = db.getCon();
            try {
                ResultSet rs = con.createStatement().executeQuery("Select * from sorry_table");
                while (rs.next()) {
                    System.out.println(rs.getString("name"));
                    oblist.add(new LeaderboardModel(rs.getString("gameid"),
                            rs.getString("name"),
                            rs.getString("moved"),
                            rs.getString("sorries"),
                            rs.getString("started"),
                            rs.getString("home")));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            col_gameid.setCellValueFactory(new PropertyValueFactory<>("gameid"));
            col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_moved.setCellValueFactory(new PropertyValueFactory<>("moved"));
            col_sorries.setCellValueFactory(new PropertyValueFactory<>("sorries"));
            col_started.setCellValueFactory(new PropertyValueFactory<>("started"));
            col_home.setCellValueFactory(new PropertyValueFactory<>("home"));

            leaderboard_table.setItems(oblist);
        }
    }
}