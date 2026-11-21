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
			ResultSet rs = stm.executeQuery("select * from employee");

			while (rs.next()) {
				System.out.print("\n" + rs.getString(1) + "  ");
				System.out.print(rs.getString(2) + "  ");
				System.out.print(rs.getString(3) + "  ");
				System.out.print(rs.getInt(4) + "  ");
				System.out.print(rs.getString(5) + "  ");

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		new jdbcApp2().Connection();
	}

}
