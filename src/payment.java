/*
 * Project title: Cash Register System
 * Part name:     payment
 * Description:   Use for print bill to customer
 * Author:        
 * Last edited:   11:11 AM DEC 13, 2017
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class payment extends Buttons {
		public payment() {}; // Constructor
		@Override
		protected void Price() {
			JButton btnPay = new JButton(); // Create button 
			btnPay.setBounds(918, 107, 313, 161); // Set location at x:918 ; y:107 ; w:313 ; h:161
			/**
			 * Action catching
			 */
			btnPay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						JOptionPane.showMessageDialog(Interface.frame, "Successful. Total is " + Interface.total, "", JOptionPane.INFORMATION_MESSAGE); // Show pop-up with total price and buttons
						Interface.textBILL.setText(null); // Set text of textBILL to default
						Interface.textTOTAL.setText(null); // Set text of textTOTAL to default
						Interface.total = 0; // Set value of total price to 0

					} catch (Exception e) {
						JOptionPane.showMessageDialog(Interface.frame, "Failed", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			Interface.frame.getContentPane().setLayout(null);
			btnPay.setIcon(new ImageIcon("G:\\src_MENU\\Menu\\Menu\\src\\Pictures\\payment.png")); // Set background picture at direction src:\Pictures\payment.png
			Interface.frame.getContentPane().add(btnPay);
	}
		@Override
		protected void Log() {}
}
