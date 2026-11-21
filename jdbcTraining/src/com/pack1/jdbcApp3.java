package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class jdbcApp3 {

    String driver = "oracle.jdbc.OracleDriver";
    String DBurl = "jdbc:oracle:thin:@localhost:1521:FREE";
    String uname = "ghost";
    String pwd = "luffy";

    void Connection() {

        try {

            Class.forName(driver);

            Connection con = DriverManager.getConnection(DBurl, uname, pwd);

            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM EMPLOYEE");

            ArrayList<String> list = new ArrayList<>();

            while (rs.next()) {

                String data = rs.getString("EID") + " " + rs.getString("EFNAME") + " " + rs.getInt("ESAL");

                list.add(data);
            }

            Collections.sort(list);

            for (String s : list) {
                System.out.println(s);
            }
            System.out.println("======================");

            Collections.sort(list, Collections.reverseOrder());

            for (String s : list) {
                System.out.println(s);
            }

        } 
        catch (Exception e) {
            
        	e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new jdbcApp3().Connection();
    }
}
