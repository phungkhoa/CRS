/*
 * Project title: Cash Register System
 * Part name:     database
 * Description:   Use for connecting to database
 * Author:        
 * Last edited:   11:19 AM DEC 13, 2017
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
	public database() { // Constructor
		dataAccess();
	}
	
	public static Connection dataAccess() {
		Connection connection = null; // Create variable connection
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/khoaphung", "root", ""); // Connect to database at link
		} catch (SQLException e) {
			System.out.println(e); // Print out errors
		}
		return connection; // Return connection of database
	}
	
	public static void main(String[] args) {
		new database();
	}
}
