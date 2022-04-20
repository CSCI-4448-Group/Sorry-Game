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
        try {
            InputStream input = getClass().getResourceAsStream("/config.properties");
            prop.load(input);
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sorryDB?useLegacyDatetimeCode=false&serverTimezone=MST", prop.getProperty("username"), prop.getProperty("password"));
            statement = con.createStatement();
            gameid = getSqlGameId();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private String getNextSqlID() {
        try {
            ResultSet set = statement.executeQuery("SELECT COALESCE(MAX(id), 0) FROM sorry_table");
            int id = 0;
            while (set.next()) {
                id = set.getInt(1);
            }
            String str_id = String.valueOf(id + 1);
            return str_id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "0";
    }

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

    public static String getGameId() {
        if (gameid.equals("0")) {
            getSqlGameId();
        }
        return gameid;
    }

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