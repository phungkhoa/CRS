/*
 * Project title: Cash Register System
 * Part name:     cancel 
 * Description:   Use for clearing all input data
 * Author:        
 * Last edited:   11:06 AM DEC 13, 2017
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class cancel extends Buttons {
	public cancel() {}; // Constructor
	@ Override
	protected void Price() {
		JButton btnCancel = new JButton(); // Create button
		/**
		 * Action catching
		 */
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interface.textBILL.setText(null); // Set text of textBILL to default
				Interface.textTOTAL.setText(null); // Set text of textTOTAL to default
				Interface.total = 0; // Set value of total price to 0
			}
		});
		btnCancel.setBounds(918, 490, 313, 161); // Set location at x:918 ; y:490 ; w:313 ; h:161
		btnCancel.setIcon(new ImageIcon("G:\\src_MENU\\Menu\\Menu\\src\\Pictures\\cancel.png")); // // Set background picture at direction src:\Pictures\cancel.png
		Interface.frame.getContentPane().add(btnCancel); // Add button to frame
		
	}
	@Override
	protected void Log() {}
}
