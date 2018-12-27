/*
 * Project title: Cash Register System
 * Part name:     Interface
 * Description:   Use for creating Graphic User Interface
 * Author:        
 * Last edited:   11:28 AM DEC 13, 2017
 */

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class Interface {

	/**
	 * Create variables used in program with static form
	 */
	public static JFrame frame;
	public static JTextArea textBILL;
	public static JTextField textTOTAL;
	public static JTabbedPane tabbedPane;
	static int total = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access") // Access by static way
			public void run() {
				try {
					Interface window = new Interface();
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
	public Interface() { // Constructor
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(); // Create frame
		frame.setAlwaysOnTop(true);// Set window is always on top of others
		frame.setResizable(false); // Set window unresizable 
		frame.setBounds(0, 0, 1280, 720); // Set window size with 1280 width and 720 height
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set window closable
		frame.getContentPane().setLayout(null); // Set layout is null

		// ======================== MENU ===========================

		tabbedPane = new JTabbedPane(JTabbedPane.TOP); // Set tabbedPane on TOP
		tabbedPane.setBounds(66, 107, 365, 431); // Set location at x:66 ; y:107 and width:365 ; height 431
		frame.getContentPane().add(tabbedPane); // Add tabbedPane to frame

		setNewTab(); // Add component to tabbedPane

		// ======================= BILL ===========================
		displayBill(frame); // Call function DISPLAYBILL
		
		// ====================== TOOLS ===========================
		displayTools(frame); // Call function DISPLAYTOOLS
		
		// ====================== SIGN IN =========================
		SignIn(); // Call function SIGNIN
		
		// ====================== SIGN UP =========================
		SignUp(); // Call function SIGNUP
	} // close initialize 

	

	// ======================= Display BILL ===========================

	private void displayBill(JFrame frame) {
		textBILL = new JTextArea(); // Create text field
		textBILL.setBounds(507, 107, 348, 430); // Set location with w:507 ; y:107 ; w:348 ; h:430
		frame.getContentPane().add(textBILL); // Add text field to frame
		textBILL.setColumns(10); // Set number of column of text field is 10
		textBILL.setEditable(false); // Set text field uneditable

		JLabel lblNewLabel = new JLabel("BILL"); // Set name of text field is Bill
		lblNewLabel.setBounds(507, 62, 111, 43); // Set location with x:506 ; y:62 ; h:111 ; w:43
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27)); // Set font of text
		frame.getContentPane().add(lblNewLabel); // Add to frame
		
		// ======================= TOTAL ==========================
		
				textTOTAL = new JTextField(); // Create text field to show total price
				textTOTAL.setHorizontalAlignment(SwingConstants.TRAILING); // Set position
				textTOTAL.setBounds(507, 583, 348, 60); // Set location with x:506 ; y:583 ; w:348 ; h:60
				frame.getContentPane().add(textTOTAL); // Add to frame
				textTOTAL.setColumns(10); // Set number of column is 10
	}



	// ======================= Display TOOLS ==========================

	private void displayTools(JFrame frame) {
		payment(frame); // Call function PAYMENT
		discount(frame); // Call function DISCOUNT
		cancel(frame); // Call function CANCEL
		}
	
	
	//======================= Payment ===========================
	private  void payment(JFrame frame) {
		payment pay = new payment(); // Create object pay from class PAYMENT
		pay.Price(); // Call function PRICE of object pay
	};
		
	
	
	// ================ DISCOUNT
	private  void discount (JFrame frame) {
		discount dis = new discount(); // Create object dis from class DISCOUNT
		dis.Price(); // Call function PRICE of object dis
	}
	
	
	// ================= CANCEL
	private  void cancel(JFrame frame) {
		cancel can = new cancel(); // Create object can from class CANCEL
		can.Price(); // Call function PRICE of object can
	}
	
	
	// ======================== Set New Tab ======================
	
	private void setNewTab() {
		menu.main(); // Call function MAIN from class MENU
	}
	
	protected Buttons signIn, signUp;
	
	// ================= Sign In
	
	private void SignIn() {
		signIn = new SignIn();
		JButton btnSignIn = new JButton("Sign In"); // Create button
		/**
		 * Action catching
		*/
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signIn.Log(); // Call function LOG from class SIGNIN
			}
		});
		btnSignIn.setBounds(1165, 11, 89, 23); // Set location at x:1165 ; y:11 ; w:89 ; h:23
		frame.getContentPane().add(btnSignIn); // Add button to frame
	}
	
	// ================ Sign Up
	
	private void SignUp() {
		signUp = new SignUp();
		JButton btnSignUp = new JButton("Sign Up"); // Create button
		/**
		 * Action catching
		*/
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signUp.Log(); // Call function LOG from class SIGNIN
			}
		});
		btnSignUp.setBounds(1066, 11, 89, 23); // Set location at x:1066 ; y:11 ; w:89 ; y:23
		frame.getContentPane().add(btnSignUp); // Add button to frame
	}
}
