/*
 * Project title: Cash Register System
 * Part name:     Sign In
 * Description:   Use for signing in with user and account in Database
 * Author:        
 * Last edited:   9:53 AM DEC 13, 2017
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SignIn extends Buttons {

	/**
	 * Create variables used in program
	 */
	private JFrame frame;
	private JTextField field_User;
	private JPasswordField field_Pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn window = new SignIn();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignIn() { // Constructor
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(); // Create frame 
		frame.setAlwaysOnTop(true); // Set frame always on top
		frame.setBounds(100, 100, 322, 235); // Set location at x:100 ; y:100 ; w:322 ; h:235
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set frame exit when click close
		frame.getContentPane().setLayout(null); // Set layout to null
		
		/**
		 * Create label SIGN IN
		 */
		JLabel lblLogIn = new JLabel("Sign In"); // Create label
		lblLogIn.setFont(new Font("Tahoma", Font.PLAIN, 26)); // Set font of label
		lblLogIn.setEnabled(false); // Set label not editable 
		lblLogIn.setBounds(101, 11, 105, 34); // Set location at x:101 y:11 ; w:105 ; h:34
		frame.getContentPane().add(lblLogIn); // Add label to frame
		
		/**
		 * Create label USER
		 */
		JLabel user = new JLabel("User"); // Create label
		user.setEnabled(false); // Set label not editable
		user.setFont(new Font("Tahoma", Font.PLAIN, 20)); // Set font of label
		user.setBounds(32, 44, 59, 36); // Set location at x:32 ; y:44 ; w:59 ; h:36
		frame.getContentPane().add(user); // Add label to frame
		
		/**
		 * Create label PASSWORD
		 */
		JLabel password = new JLabel("Password"); // Create label
		password.setFont(new Font("Tahoma", Font.PLAIN, 20)); // Set font of label
		password.setEnabled(false); // Set label not editable
		password.setBounds(32, 92, 92, 34); // Set location at x:32 ; y:92 ; w:92 ; h:34
		frame.getContentPane().add(password); // Add label to frame
		
		/**
		 * Create text field FIELD_USER
		 */
		field_User = new JTextField(); // Create text field
		field_User.setBounds(134, 56, 141, 20); // Set location at x:134 ; y:56 ; w:141 ; h:20
		frame.getContentPane().add(field_User); // Add text field to frame
		field_User.setColumns(10); // Set column of text field to 10
		
		/**
		 * Create password field FIELD_PASS
		 */
		field_Pass = new JPasswordField(); // Create text field 
		field_Pass.setBounds(134, 101, 141, 24); // Set location at x:134 ; y:101 ; w:141 ; h:24
		frame.getContentPane().add(field_Pass); // Add text field to frame
		
		/** 
		 * Create 
		 */
		JButton btnLogIn = new JButton("Sign In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkAccount();
			}
		});
		btnLogIn.setBounds(32, 151, 89, 23);
		frame.getContentPane().add(btnLogIn);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose(); // Click to CLOSE
			}
		});
		btnClose.setBounds(186, 151, 89, 23);
		frame.getContentPane().add(btnClose);
	}
	
	
	/**
	 * Check input data is correct or not
	 */
	private void checkAccount() {
		@SuppressWarnings("deprecation")
		String password = User.hashFunction(field_Pass.getText()); // Hash INPUT PASSWORD
		String username = field_User.getText();
		
		if(username == "" || password == "") {
			JOptionPane.showMessageDialog(frame, "Invalid user/password", "Error", 2);
		}
		else {
			Connection connection;
			PreparedStatement ps;
			try {
				connection = database.dataAccess();
				ps = connection.prepareStatement("Select * from ACCOUNT where USER=? and PASSWORD=?");
				ps.setString(1, username);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				
				if (rs.next()) {
					/**
					 * Check if data in 2 text fields are same with those in DB
					 */
					JOptionPane.showMessageDialog(frame, "Successful", "Message", 1);
					frame.dispose(); // Turn off Sign In frame
				} 
				else {
					JOptionPane.showMessageDialog(frame, "Invalid user/password", "Error", 1);
					/** 
					 * Erase data in 2 text fields
					 */
					field_User.setText("");
					field_Pass.setText("");
					field_User.requestFocus();
				}
			} catch (SQLException e) {
				System.out.println("No database connection");
			}
		}
	}

	@Override
	protected void Price() {}

	@Override
	protected void Log() {
		main(null);
	}
}
