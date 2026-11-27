package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JdbcApp3 {

	static String driver = "oracle.jdbc.OracleDriver";
	static String DBurl = "jdbc:oracle:thin:@localhost:1521:FREE";
	static String DBuname = "ghost";
	static String DBpwd = "luffy";

	Scanner sc = new Scanner(System.in);

	public Connection getConn() {

		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(DBurl, DBuname, DBpwd);

		} 
		catch (Exception e) {
			e.printStackTrace();

		}
		return con;
	}

	void patientOperations() {

		try {
			Connection con = getConn();
			PreparedStatement ps1 = con.prepareStatement("insert into patient values(?,?,?,?)");
			PreparedStatement ps2 = con.prepareStatement("select * from patient");
			PreparedStatement ps3 = con.prepareStatement("select * from patient where pid = ? ");
			PreparedStatement ps4 = con.prepareStatement("update patient set age = ? where pid = ? ");
			PreparedStatement ps5 = con.prepareStatement("delete from patient where pid = ? ");

			while (true) {

				System.out.println("Welcome to Patient Database");
				System.out.println(" 1) Add Patient Data\n 2) View Patient Data\n 3) Reterive Patient Data\n 4) Update Patient Data\n 5) Delete Patient Data\n 6) EXIT");

				int choice = Integer.parseInt(sc.nextLine());

				switch (choice) {

				case 1 -> {

					System.out.println("Adding Patient Data");
					System.out.println("Enter Patient Id : ");
					String pid1 = sc.nextLine();
					System.out.println("Enter Patient Name : ");
					String pname1 = sc.nextLine();
					System.out.println("Enter Patient Age : ");
					int page = Integer.parseInt(sc.nextLine());
					System.out.println("Enter Patient Contact : ");
					long pcontact = Long.parseLong(sc.nextLine());

					ps1.setString(1, pid1);
					ps1.setString(2, pname1);
					ps1.setInt(3, page);
					ps1.setLong(4, pcontact);

					int rowCount1 = ps1.executeUpdate();
					if (rowCount1 == 1) {
						System.out.println(pid1 + " record inserted!!!");
					} 
					else {
						System.out.println("Record insertion Failed");
					}
					break;
				}
				case 2 -> {
					System.out.println("View Patient Data");
					ResultSet rs1 = ps2.executeQuery();
					while (rs1.next()) {
						System.out.println(
								rs1.getString(1) + " " + rs1.getString(2) + " " + rs1.getInt(3) + " " + rs1.getLong(4));
					}
					break;
				}
				case 3 -> {
					System.out.println("Search Patient Data");
					System.out.println("Enter Patient Id : ");
					String pid = sc.nextLine();

					ps3.setString(1, pid);
					ResultSet rs2 = ps3.executeQuery();

					if (rs2.next()) {
						System.out.println(
								rs2.getString(1) + " " + rs2.getString(2) + " " + rs2.getInt(3) + " " + rs2.getLong(4));

					} 
					else {
						System.out.println("Patient record Not Found!!!");
					}

					break;
				}
				case 4 -> {
					System.out.println("Update Patient Data");
					System.out.println("Enter Patient Id : ");
					String pid = sc.nextLine();
					System.out.println("Enter Patient Age : ");
					int page = Integer.parseInt(sc.nextLine());

					ps4.setInt(1, page);
					ps4.setString(2, pid);

					int rowCount = ps4.executeUpdate();
					if (rowCount == 1) {
						System.out.println(pid + " Record updated successfully");
					} 
					else {
						System.out.println("Record updation Failed");
					}
					break;
				}
				case 5 -> {
					System.out.println("Delete Patient Data");
					System.out.println("Enter Patient Id : ");
					String pid = sc.nextLine();

					ps5.setString(1, pid);

					int rowCount = ps5.executeUpdate();
					if (rowCount == 1) {
						System.out.println(pid + " Record Deleted successfully");
					} 
					else {
						System.out.println("Invalid Record Deletion input");
					}
					break;
				}
				case 6 -> {
					System.out.println("Thankyou <3");
					System.exit(0);
					break;
			    }
				
			   }
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		JdbcApp3 obj = new JdbcApp3();
		obj.patientOperations();
	}
}
