/*
 * Project title: Cash Register System
 * Part name:     User
 * Description:   Use to MANAGE users in Database. PASSWORD is HASHED before transfer to Database
 * Author:        
 * Last edited:   9:52 AM DEC 13, 2017
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
	private String username;
	private String password;
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	private Scanner scanner = new Scanner(System.in);
	
	public User() {
		connectDBS();
		manageUser();
	}
	
	/**
	 * Hash function for encrypting PASSWORD
	 */
	public static String hashFunction(String message) {
		String digest = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(message.getBytes("UTF-8"));
			
			/*
			 * Convert byte array to Hexadecimal String
			 */
			StringBuilder sb = new StringBuilder(2 * hash.length);
			for (byte b : hash) {
				sb.append(String.format("%02x", b & 0xff));
			}
			
			digest = sb.toString();
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
			
		}
		
		return digest;
	}
	
	/**
	 * Connect with DATABASE
	 */
	private void connectDBS() {
		connection = database.dataAccess();
	}
	
	/**
	 * Add new USER
	 */
	private void addUser() {
		/*
		 * Input USERNAME and PASSWORD
		 */
		scanner.nextLine();
		System.out.print("Input username: ");
		username = scanner.nextLine();
		System.out.print("Input password: ");
		password = hashFunction(scanner.nextLine()); // Hash PASSWORD
		
		/*
		 * Statement for INSERT new account into ACCOUNT
		 */
		ps = null;
		try {
			ps = connection.prepareStatement("Select USER from ACCOUNT where USER = ?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if(!rs.next()) { // Check if USERNAME exist 
				try {
					ps = connection.prepareStatement("Insert into ACCOUNT value (?, ?)");
					ps.setString(1, username);
					ps.setString(2, password);
					
					ps.execute(); // Execute statement
					
					System.out.println("Successful\n");
				} catch (SQLException e) {
					System.out.println(e);
				}
				
			}
			else {
				System.out.println("Failed! Username exists\n");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Delete USER
	 */
	private void deleteUser() {
		/*
		 * Input USERNAME 
		 */
		scanner.nextLine();
		System.out.print("Input username: ");
		username = scanner.nextLine();
		
		/*
		 * Statement for DELETING account in ACCOUNT
		 */
		ps = null;
		try {
			ps = connection.prepareStatement("Select USER from ACCOUNT where USER = ?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if(rs.next()) { // Check if USERNAME exist 
				try {
					ps = connection.prepareStatement("Delete from ACCOUNT where USER = ?");
					ps.setString(1, username);
					
					ps.execute(); // Execute statement
					
					System.out.println("Successful\n");
				} catch (SQLException e) {
					System.out.println(e);
				}
				
			}
			else {
				System.out.println("Username does not exist\n");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Delete all USER
	 */
	
	private void deleteAll() {
		try {
			ps = connection.prepareStatement("Delete from ACCOUNT");
			
			ps.execute(); // execute statement
			
			System.out.println("Successfull\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Modify USER
	 */
	private void modifyUser() {
		/*
		 * Input USERNAME and PASSWORD
		 */
		scanner.nextLine();
		System.out.print("Input username: ");
		username = scanner.nextLine();
		System.out.print("Input password: ");
		password = hashFunction(scanner.nextLine()); // Hash PASSWORD
		
		/*
		 * Statement for MODIFYING account in ACCOUNT
		 */
		ps = null;
		
		try {
			ps = connection.prepareStatement("Select USER from ACCOUNT where USER = ?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if(rs.next()) { // Check if USERNAME exist 
				try {
					ps = connection.prepareStatement("Update ACCOUNT set PASSWORD = ? where USER = ?");
					ps.setString(1, password);
					ps.setString(2, username);
					
					ps.execute(); // Execute statement
					
					System.out.println("Successful\n");
				} catch (SQLException e) {
					System.out.println(e);
				}
				
			}
			else {
				System.out.println("Username does not exist\n");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
	}
	
	/**
	 * Show all USER
	 */
	private void showUser() {
		try {
			ps = connection.prepareStatement("Select * from ACCOUNT");
			rs = ps.executeQuery();
			
			System.out.printf("%-22s%20s\n", "USERNAME", "HASHED PASSWORD");
			System.out.println("----------------------------------------------------");
			while(rs.next()) {
				System.out.printf("%-20s%20s\n", rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("");
	}
	
	/**
	 * Choices
	 */
	private void choice() {
		int choice = 0;
		do {
			System.out.printf("1. Add user\n"
					+ "2. Delete user\n"
					+ "3. Delete all user\n"
					+ "4. Modify user\n"
					+ "5. Show all user\n"
					+ "6. Exit\n"
					+ "Choose method: ");
			choice = scanner.nextInt();
			if (choice < 1 || choice > 6) {
				System.out.println("\nInvalid choice\n");
			}
		}while(choice < 1 || choice > 6);
		
		switch (choice) {
			case 1:
				addUser();
				break;
			case 2:
				deleteUser();
				break;
			case 3:
				deleteAll();
				break;
			case 4:
				modifyUser();
				break;
			case 5:
				showUser();
				break;
			default:
				System.out.println("\nSystem exit..........");
				System.exit(0);
		}
	}
	
	/**
	 * Manage ACCOUNT in DATABASE
	 */
	private void manageUser() {
		while (true) { // Loop until press key 6
			choice();
		}
	}
	
	public static void main(String[] args) {
		new User();
	}
}
