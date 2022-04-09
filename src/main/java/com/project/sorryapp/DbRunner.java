package com.project.sorryapp;

import java.sql.*;


public class DbRunner {
    Connection con;
    Statement statement;

    public DbRunner() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sorryDB?useLegacyDatetimeCode=false&serverTimezone=MST", "root", "");
            statement = con.createStatement();
        } catch (SQLException e) {
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

//    public void crud() {
//
//        create(session);
//        read(session);
//
//        update(session);
//        read(session);
//
//        delete(session);
//        read(session);
//
//        session.close();
//    }

//    private void delete() {
//        System.out.println("Deleting mondeo record...");
//        DbData data = (DbData) session.get(DbData.class, "data");
//
//        session.beginTransaction();
//        session.delete(data);
//        session.getTransaction().commit();
//    }
//
//    protected void update() {
//        System.out.println("Updating table...");
//        DbData data = (DbData) session.get(DbData.class, "data");
//
//
//        session.beginTransaction();
//        session.saveOrUpdate(data);
//        session.getTransaction().commit();
//    }
//
    public void create(String nameOfPlayer, int numSpacesMoved, int numSorries, int numPawnsStarted, int numPawnsHome) {
        try {
            String moved = String.valueOf(numSpacesMoved);
            String sorries = String.valueOf(numSorries);
            String started = String.valueOf(numPawnsStarted);
            String home = String.valueOf(numPawnsHome);
            String id = getNextSqlID();
            String sql = "Insert into sorry_table Values (" + id + ", '" + nameOfPlayer + "', " + moved + ", " + sorries + ", " + started + ", " + home + ")";
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
            String started = String.valueOf(curRow[2] + numPawnsStarted);
            String home = String.valueOf(curRow[3] + numPawnsHome);
            String sql = "update sorry_table set moved = " + moved + ", sorries = " +
                    sorries + ", started = " + started + ", home = " + home + " where name = '" + nameOfPlayer + "'";
            System.out.println(sql);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int[] read(String nameOfPlayer) {
        int[] result = new int[4];
        try {
            String sql = "Select * from sorry_table where name = '" + nameOfPlayer + "'";
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