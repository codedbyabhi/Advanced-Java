package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;


public class JdbcApp1 {

	String driver = "oracle.jdbc.OracleDriver";
	String DBurl = "jdbc:oracle:thin:@//localhost:1521/free";
	String uname = "ghost";
	String pwd = "luffy";

	void connect() {

		System.out.println("Connecting to the Database");

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(DBurl,uname,pwd);
			System.out.println("Connection created!!!");
			con.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		JdbcApp1 obj = new JdbcApp1();
		obj.connect();
	}
}
