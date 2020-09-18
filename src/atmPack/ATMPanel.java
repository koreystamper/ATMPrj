package atmPack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 * Write a description of class ATMPanel here.
 * 
 * @author Roger Ferguson
 * @version April 7, 2019
 */
public class ATMPanel extends JPanel{
	
	private ATM jar;

	NumberFormat fmt = NumberFormat.getCurrencyInstance();

	JButton takeOutHFTButton;

	JTextField hundredField, fiftyField, twentyField;


	public ATMPanel(){ 

		// create the game object as well as the ATMGUI Frame
		jar = new ATM();

		// set the layout to GridBag
		setLayout(new GridLayout(13,2));
		setBackground(Color.lightGray);

		// get Die #2 from game and place on ATMGUI
		hundredField = new JTextField("0", 3);
		add(hundredField);
		add(new JLabel("Hundreds:"));

		fiftyField = new JTextField("0", 3);
		add(fiftyField);
		add(new JLabel("Fifties:"));

		twentyField = new JTextField("0", 3);
		add(twentyField);
		add(new JLabel("Twenties:"));

		takeOutHFTButton = new JButton("Take Out with H,F,T");
		add(takeOutHFTButton);


		add(new JLabel("Total:"));

		add (new JLabel(""));

		add (new JLabel ("Current State ATM"));
		add (new JLabel(""));
		add (new JLabel("Hundreds"));
		add (new JLabel("Fifties"));
		add (new JLabel("Twenties"));

		// register the listeners

		takeOutHFTButton.addActionListener(new ButtonListener());

	}


	/****************************************************************
Inner class to repond to the user action
     ****************************************************************/
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent event) {

			int hundreds, fifites, twenties;

			if (event.getSource() == takeOutHFTButton) {
				try {
					hundreds = Integer.parseInt(hundredField.getText());
					fifites = Integer.parseInt(fiftyField.getText());
					twenties = Integer.parseInt(twentyField.getText());
					jar.takeOut(hundreds, fifites, twenties);
				} catch (NumberFormatException io) {
					JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, "Not enough specified coins for this operation");
				}
			}
		}
	}
}
