package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JdbcApp4 {

	static String driver = "oracle.jdbc.OracleDriver";
	static String DBurl = "jdbc:oracle:thin:@localhost:1521:FREE";
	static String DBuname = "ghost";
	static String DBpwd = "luffy";

	Scanner sc = new Scanner(System.in);

	public Connection getConn() {

		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(DBurl,DBuname,DBpwd); 

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	public void libraryBooks(){
		try {
		Connection con = getConn();
		PreparedStatement ps1 = con.prepareStatement("insert into library values(?,?,?,?,?)");
		PreparedStatement ps2 = con.prepareStatement("select * from library where bookId = ? ");
		PreparedStatement ps3 = con.prepareStatement("delete from library where bookId = ? ");
		PreparedStatement ps4 = con.prepareStatement("select * from library");
		
		while(true) {
			System.out.println("Welcome to Library Enter your Choice");
			System.out.println(" 1) Add Book\n 2) Retreving Book\n 3) Delete Book\n 4) Display and Result\n 5) EXIT");
			
			int choice = Integer.parseInt(sc.nextLine());
			
			switch(choice) {
			
			case 1 ->{
					System.out.println("Library DataBase `~`");
					System.out.println("Add Book Details");
					System.out.println("Enter Book Id : ");
					String id = sc.nextLine();
					System.out.println("Enter Book Name : ");
					String name = sc.nextLine();
					System.out.println("Enter Author Name : ");
					String autherName = sc.nextLine();
					System.out.println("Enter Genere : ");
					String genere = sc.nextLine();
					System.out.println("Enter Book Cost : ");
					int cost = Integer.parseInt(sc.nextLine());
					
					ps1.setString(1, id);
					ps1.setString(2, name);
					ps1.setString(3, autherName);
					ps1.setString(4, genere);
					ps1.setInt(5, cost);
					
					int rowCount = ps1.executeUpdate();
					if(rowCount==1) {
						System.out.println(id+" record inserted successfully.");
					}
					else {
						System.out.println("Record Insertion Failed");
					}
					break;
				}
			case 2 ->{
					System.out.println("Library DataBase `~`");
					System.out.println("Enter BookId : ");
					String id = sc.nextLine();
					
					ps2.setString(1,id);
					ResultSet rs = ps2.executeQuery();
					
					if(rs.next()) {
					System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5));
					}
					else {
						System.out.println("Book not Found");
					}
					break;
				}
			case 3 ->{
					System.out.println("Library DataBase `~`");
					System.out.println("Enter BookId : ");
					String id = sc.nextLine();
					
					ps3.setString(1, id);
					
					int rowCount = ps3.executeUpdate();
					if(rowCount==1) {
						System.out.println(id+" Record Deleted successfully");
					}
					else {
						System.out.println("Recored Deletion Failed!!!");
					}
					break;
				}
			case 4 ->{
				System.out.println("Library DataBase `~`");
				ResultSet rs = ps4.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5));
				}
				break;
				
				}
			case 5 ->{
					System.out.println("Thankyou for Visiting");
					System.exit(0);
					break;
				}
			default ->{
					System.out.println("Invalid input please Insert 1 to 5 only.");
				}
			}
		}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		JdbcApp4 obj = new JdbcApp4();
		obj.libraryBooks();
	}

}