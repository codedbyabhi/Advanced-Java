package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbcApp2 {

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

//			while(rs.next()) {
//				
//				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
//			}
			while (rs.next()) {
				System.out.println(rs.getString("EID") + " " + rs.getString("EFNAME") + " " + rs.getInt("ESAL"));
//				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getInt(4));

			}

		} 
		catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		new jdbcApp2().Connection();
	}

}
