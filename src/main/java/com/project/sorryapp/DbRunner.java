package com.project.sorryapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class DbRunner {
    Connection con;
    static Statement statement;
    Properties prop = new Properties();
    static String gameid = "0";

    public Connection getCon() {
        return con;
    }

    public DbRunner() {
        // See source: https://www.baeldung.com/java-connect-mysql
        try {
            InputStream input = getClass().getResourceAsStream("/config.properties"); // Pulls credentials from config file so we don't need to write them in plaintext here.
            prop.load(input);
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sorryDB?useLegacyDatetimeCode=false&serverTimezone=MST", prop.getProperty("username"), prop.getProperty("password")); // Saves connection as con to use
            statement = con.createStatement(); // Used to execute commands in DB
            gameid = getSqlGameId(); // Statick for this game, so can be saved and only called once
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    // Gets next id to use for inserting into the database. Want to make sure we are trying to insert a primary key that is not already used
    private String getNextSqlID() {
        try {
            ResultSet set = statement.executeQuery("SELECT COALESCE(MAX(id), 0) FROM sorry_table");
            int id = 0;
            while (set.next()) {
                id = set.getInt(1);
            }
            String str_id = String.valueOf(id + 1);
            return str_id; // Returns the id it finds to use
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "0";
    }

    // Gets the sql game id which doesn't change during the game.
    private static String getSqlGameId() {
        try {
            ResultSet set = statement.executeQuery("SELECT COALESCE(MAX(gameid), 0) FROM sorry_table");
            int id = 0;
            while (set.next()) {
                id = set.getInt(1);
            }
            return String.valueOf(id + 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "0";
    }

    // Gets game id, querying database if game id is not already saved.
    public static String getGameId() {
        if (gameid.equals("0")) {
            getSqlGameId();
        }
        return gameid;
    }

    // Inserts into database with 4 rows, 1 for each player with default values
    public void create() {
        try {
            String id = getNextSqlID();
            String sql = "Insert into sorry_table Values (" + id + ", " + gameid + ", 'red', 0, 0, 0, 0)";
            System.out.println(sql);
            statement.executeUpdate(sql);

            id = getNextSqlID();
            sql = "Insert into sorry_table Values (" + id + ", " + gameid + ", 'blue', 0, 0, 0, 0)";
            System.out.println(sql);
            statement.executeUpdate(sql);

            id = getNextSqlID();
            sql = "Insert into sorry_table Values (" + id + ", " + gameid + ", 'yellow', 0, 0, 0, 0)";
            System.out.println(sql);
            statement.executeUpdate(sql);

            id = getNextSqlID();
            sql = "Insert into sorry_table Values (" + id + ", " + gameid + ", 'green', 0, 0, 0, 0)";
            System.out.println(sql);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Updates values in DB with values passed in. numSpacesMoved and numSorries increment the current value in the DB row, but numPawnsStarted and numPawnsHome replace the value.
    public void update(String nameOfPlayer, int numSpacesMoved, int numSorries, int numPawnsStarted, int numPawnsHome) {
        try {
            int[] curRow = read(nameOfPlayer);
            String moved = String.valueOf(curRow[0] + numSpacesMoved);
            String sorries = String.valueOf(curRow[1] + numSorries);
            String started = String.valueOf(numPawnsStarted);
            String home = String.valueOf(numPawnsHome);
            String sql = "update sorry_table set moved = " + moved +
                    ", sorries = " + sorries + ", started = " + started +
                    ", home = " + home + " where name = '" + nameOfPlayer +
                    "' and gameid = " + gameid;
            System.out.println(sql);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Just gets the values for the given player for this game id.
    public int[] read(String nameOfPlayer) {
        int[] result = new int[4];
        try {
            String sql = "Select * from sorry_table where name = '" + nameOfPlayer + "' and gameid = " + gameid;
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                result[0] = set.getInt("moved");
                result[1] = set.getInt("sorries");
                result[2] = set.getInt("started");
                result[3] = set.getInt("home");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Row Values: " + result[0] + " " + result[1] + " " + result[2] + " " + result[3]);
        return result;
    }
}