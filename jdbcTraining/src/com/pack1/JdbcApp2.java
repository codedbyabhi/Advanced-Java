package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcApp2 {

    static String driver = "oracle.jdbc.OracleDriver";
    static String DBurl = "jdbc:oracle:thin:@localhost:1521:FREE";
    static String uname = "ghost";
    static String pwd = "luffy";

    Scanner sc = new Scanner(System.in);

    static Connection getCon() {
        Connection con = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(DBurl, uname, pwd);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
    
    public void meth1() {

        System.out.println("Retrieving data from Employee Table");

        try (Connection con = getCon();
             Statement stm = con.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM EMPLOYEE")) {

            while (rs.next()) {
                System.out.println(rs.getString(1) + " " +rs.getString(2) + " " +rs.getString(3) + " " +rs.getString(4) + " " +rs.getString(5));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void meth2() {

        try (Connection con = getCon();
             Statement stm = con.createStatement()) {

            System.out.println("Enter EmpId : ");
            String eid = sc.nextLine();

            System.out.println("Enter First Name : ");
            String efname = sc.nextLine();

            System.out.println("Enter Last Name : ");
            String elname = sc.nextLine();

            System.out.println("Enter Salary : ");
            int esal = Integer.parseInt(sc.nextLine());

            System.out.println("Enter Address : ");
            String eadd = sc.nextLine();

            int rowCount = stm.executeUpdate(
                "INSERT INTO EMPLOYEE VALUES ('" + eid + "','" +efname + "','" + elname + "'," + esal + ",'" + eadd + "')");

            if (rowCount == 1) {

                System.out.println(eid + " Record inserted!!!");

                System.out.println("Do you want to view the data (Y/N)");
                char choice = sc.nextLine().charAt(0);

                switch (choice) {
                    case 'y', 'Y' -> meth1();
                    case 'n', 'N' -> System.out.println("Thankyou for entering the Data!!!");
                    default -> System.out.println("Invalid input");
                }

            } 
            else {
                throw new RuntimeException("Data not inserted into Employee Table.");
            }

        } catch (SQLException e) {

            if (e.getErrorCode() == 1) {
                System.out.println("Duplicate eid are Not allowed!!!");
                meth2();
            } else {
                e.printStackTrace();
            }

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void meth3() {

        System.out.println("Retrieve specific data from Employee Table");

        try (Connection con = getCon();
             Statement stm = con.createStatement()) {

            System.out.println("Enter the eid you want to retrieve");
            int eid = sc.nextInt();
            sc.nextLine();

            ResultSet rs = stm.executeQuery("SELECT * FROM EMPLOYEE WHERE EID='" + eid + "'");

            if (rs.next()) {

                System.out.println(rs.getString(1) + " " +rs.getString(2) + " " +rs.getString(3) + " " +rs.getString(4) + " " +rs.getString(5));

            } 
            else {
                System.out.println(eid + " record not found");
            }

            rs.close();

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void meth4() {

        System.out.println("Update salary in Employee Table");

        try (Connection con = getCon();
             Statement stm = con.createStatement()) {

            System.out.println("Enter employee salary and employee ID");

            int salary = sc.nextInt();
            int eid = sc.nextInt();
            sc.nextLine();

            int rowCount = stm.executeUpdate("UPDATE EMPLOYEE SET ESAL='" + salary +"' WHERE EID='" + eid + "'");

            if (rowCount == 1) {

                System.out.println(eid + " record Updated");

                System.out.println("Do you want to see the table [y/n]");
                char choice = sc.nextLine().charAt(0);

                switch (choice) {
                    case 'Y', 'y' -> meth1();
                    case 'n', 'N' ->
                        System.out.println("Thanks for updating the data");
                    default -> System.out.println("Invalid input");
                }

            } 
            else {
                System.out.println(eid + " record not found, nothing updated");
            }

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void meth5() {

        System.out.println("Delete specific data from Employee Table");

        try (Connection con = getCon();
             Statement stm = con.createStatement()) {

            System.out.println("Enter employee ID you want to delete from table");

            int eid = sc.nextInt();
            sc.nextLine();

            int rowCount = stm.executeUpdate("DELETE FROM EMPLOYEE WHERE EID='" + eid + "'");

            if (rowCount == 1) {

                System.out.println(eid + " record Deleted");

                System.out.println("Do you want to see the table [y/n]");
                char choice = sc.nextLine().charAt(0);

                switch (choice) {
                    case 'Y', 'y' -> meth1();
                    case 'n', 'N' ->
                        System.out.println("Thanks for deleting the data");
                    default -> System.out.println("Invalid input");
                }

            } 
            else {
                System.out.println(
                    eid + " record not found, nothing deleted");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        JdbcApp2 obj = new JdbcApp2();

//        	obj.meth1();
//      	obj.meth2();
//       	obj.meth3();
//        	obj.meth4();
        	obj.meth5(); 
    }
}
