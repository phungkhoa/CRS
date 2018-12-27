/*
 * Project title: Cash Register System
 * Part name:     Sign Up
 * Description:   Use for creating new account in Database
 * Author:        
 * Last edited:   9:53 AM DEC 13, 2017
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUp extends Buttons {

	private JFrame frame;
	private JTextField field_User;
	private JPasswordField field_Pass;
	private JPasswordField field_RePass;
	private JLabel lblAlertUser;
	private JLabel lblAlertPass;
	private JLabel lblAlertRePass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
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
	public SignUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 487, 363);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setEnabled(false);
		lblSignUp.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblSignUp.setBounds(174, 11, 119, 41);
		frame.getContentPane().add(lblSignUp);
		
		JLabel lblUserName = new JLabel("User name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUserName.setEnabled(false);
		lblUserName.setBounds(45, 53, 105, 26);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setEnabled(false);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPassword.setBounds(45, 104, 92, 26);
		frame.getContentPane().add(lblPassword);
		
		field_User = new JTextField();
		field_User.setBounds(211, 61, 214, 20);
		frame.getContentPane().add(field_User);
		field_User.setColumns(10);
		
		JLabel lblRetype = new JLabel("Re-type");
		lblRetype.setEnabled(false);
		lblRetype.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblRetype.setBounds(47, 157, 90, 26);
		frame.getContentPane().add(lblRetype);
		
		field_Pass = new JPasswordField();
		field_Pass.setBounds(211, 112, 214, 20);
		frame.getContentPane().add(field_Pass);
		
		field_RePass = new JPasswordField();
		field_RePass.setBounds(211, 165, 214, 20);
		frame.getContentPane().add(field_RePass);
		
		/**
		 * Label Alert
		 */
		lblAlertUser = new JLabel("");
		lblAlertUser.setEnabled(false);
		lblAlertUser.setBounds(211, 87, 214, 14);
		frame.getContentPane().add(lblAlertUser);
		
		lblAlertPass = new JLabel("");
		lblAlertPass.setEnabled(false);
		lblAlertPass.setBounds(211, 140, 214, 14);
		frame.getContentPane().add(lblAlertPass);
		
		lblAlertRePass = new JLabel("");
		lblAlertRePass.setEnabled(false);
		lblAlertRePass.setBounds(211, 189, 214, 14);
		frame.getContentPane().add(lblAlertRePass);
		
		/**
		 * Buttons
		 */
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAccount();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSignUp.setBounds(84, 245, 119, 41);
		frame.getContentPane().add(btnSignUp);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClose.setBounds(262, 245, 112, 41);
		frame.getContentPane().add(btnClose);
	}
	
	/**
	 * Get USERNAME
	 * Check if USERNAME exist or not
	 * If exist => false
	 * else     => true
	 */
	private boolean checkUserName() {
		Connection connection;
		PreparedStatement ps;
		ResultSet rs;
		
		String username = field_User.getText();
		
		if (username.equals("")) {
			System.out.println("Space");
			return false;
		}
		
		else {
			try {
				connection = database.dataAccess();
				ps = connection.prepareStatement("Select USER from ACCOUNT");
				rs = ps.executeQuery();
			
				while(rs.next()) {
					if (username.equals(rs.getString(1))) {
						System.out.println("Duplicate");
						return false;
					}
					return true;
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		System.out.println("Connection");
		return false;
	}
	
	/** 
	 * Check PASSWORD
	 */
	private boolean checkPassword() {
		@SuppressWarnings("deprecation")
		String password = field_Pass.getText();
		
		if (password.equals("")) {
			return false;
		}
	
		return true;
	}
	
	/**
	 * Check if PASSWORD same as RE-TYPE
	 */
	private boolean checkRePassword() {
		@SuppressWarnings("deprecation")
		String password = field_Pass.getText();
		@SuppressWarnings("deprecation")
		String rePassword = field_RePass.getText();
		
		if (rePassword.equals("")) {
			return false;
		}
		else {
			if (!(rePassword.equals(password))) {
				return false;
			}
		}
		
		return true;
	}

	private void createAccount() {
		lblAlertUser.setText("");
		lblAlertPass.setText("");
		lblAlertRePass.setText("");
		
		if (checkUserName() == false) {
			lblAlertUser.setText("Invalid user name");
			System.out.print("Invalid");
			System.out.println(field_User.getText());
		}
		if (checkPassword() == false) {
			lblAlertPass.setText("Invalid password");
		}
		if (checkRePassword() == false) {
			lblAlertRePass.setText("Invalid re-type password");
		}
		if (checkUserName() == true && checkPassword() == true && checkRePassword() == true) {
			/*
			 * Create new ACCOUNT
			 */
			String username = field_User.getText();
			@SuppressWarnings("deprecation")
			String password = User.hashFunction(field_Pass.getText());
			PreparedStatement ps;
			Connection connection = database.dataAccess();
			
			try {
				ps = connection.prepareStatement("Insert into ACCOUNT value (?, ?)");
				ps.setString(1, username);
				ps.setString(2, password);
				
				ps.execute();
				
				JOptionPane.showMessageDialog(frame, "Successful", "", 1);
				
				/**
				 * Reset all fields to default
				 */
				field_User.setText("");
				field_Pass.setText("");
				field_RePass.setText("");
				
			} catch (SQLException e) {
				System.out.println(e);
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
