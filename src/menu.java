/*
 * Project title: Cash Register System
 * Part name:     database
 * Description:   Use for connecting to database
 * Author:        
 * Last edited:   11:19 AM DEC 13, 2017
 */

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JPanel;

public class menu {
	public static void main() {
		setNewTab(); // Call function SETNEWTAB
	}
	private static void setNewTab() {

		/**
		 * Create variables used in program
		 */
		Scanner scanner;
		JPanel jp;
		
		/**
		 * Load data from text files
		 */
		int width = 129; // Set width for print data
		int height = 57; // Set height for print data
		try {
			scanner = new Scanner(new File("G:\\Users\\Khoa\\eclipse-workspace\\CRS\\src\\prices.txt")); // Add directory
			for (int j = 0; j < 4; j++) { // create 4 tabs
				jp = new JPanel();
				jp.setLayout(null);

				int x = 30;
				int y = 13;
				Button[] b = new Button[4];
				for (int i = 0; i < 4; i++) {
					b[i] = new Button();
					jp.add(b[i]);
				}

				String str = scanner.nextLine();
				Interface.tabbedPane.add(str, jp);

				for (int i = 0; i < 2; i++) { 	// create 2 first buttons
					str = scanner.nextLine();
					b[i].setLabel(str);
					b[i].setBounds(x, y, width, height);
					b[i].addActionListener(new billActionListener(b[i].getLabel()));
					
					x += width + 20; // loop 2 times

				}
				y += height + 20; // move button down 20
				x -= width + 20; // minus 2 times
				x -= width + 20;
				for (int i = 2; i < 4; i++) {	// create 2 last buttons
					str = scanner.nextLine();
					b[i].setLabel(str);
					b[i].setBounds(x, y, width, height);
					
					b[i].addActionListener(new billActionListener(b[i].getLabel()));
					x += width + 20;
				}
			}

			scanner.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found!"); // Print out errors
		}
	}
	
	public static class billActionListener implements ActionListener {
		String str;
		public billActionListener(String str) { // constructor
			this.str = str;
		}
		
		public void actionPerformed(ActionEvent e) {
			{
				String[] s = str.split("\\s");
				int itemPrice = Integer.parseInt(s[s.length-1]);
				Interface.total += itemPrice;
				Interface.textBILL.append(str + "\n" );
				Interface.textTOTAL.setText(Integer.toString(Interface.total));
			
		}
	}

	}
}
