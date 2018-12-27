/*
 * Project title: Cash Register System
 * Part name:     discount
 * Description:   Use for inputing discount percentage and using to re-count total price
 * Author:        
 * Last edited:   10:53 AM DEC 13, 2017
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class discount extends Buttons {
	public discount() {}; // Constructor
	@ Override
	protected void Price(){
		JButton btnDiscount = new JButton(); // Create button
		/**
		 * Action catching
		 */
		btnDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String discountNumber = JOptionPane.showInputDialog(Interface.frame, "Input discount number"); // Show pop-up to input discount percentage
				int percent = Integer.parseInt(discountNumber); // Change percentage from string to integer
				
				Interface.textTOTAL.setText(Integer.toString(Interface.total)); // ?
				
				Interface.total *= ((100-percent) /100); 
				Interface.textTOTAL.setText(Integer.toString(Interface.total)); // Change total price in textTOTAL of class Interface
			}
		});
		btnDiscount.setBounds(918, 298, 313, 161); // Set location at x:918 ; y:298 ; w:313 ; h:161
		btnDiscount.setIcon(new ImageIcon("G:\\src_MENU\\Menu\\Menu\\src\\Pictures\\discount.png")); // Set background picture at direction src:\Pictures\discount.png
		Interface.frame.getContentPane().add(btnDiscount); // Add button to frame
	}
	@Override
	protected void Log() {}
	
}
