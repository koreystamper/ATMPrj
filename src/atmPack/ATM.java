package atmPack;
/********************************************************
 *
 * Project 1 of CIS 163 - ATMProject
 * 	Creates ATM objects that can be manipulated with cash denominations
 * 	of hunred, fifty, and twenty dollar bills. Users can withdraw and
 * 	deposit the same denominations. Users can also save and load
 * 	past ATM object values.
 *

 * @author Korey Stamper
 *
 ********************************************************/

import java.io.*;
import java.util.Scanner;

public class ATM extends Object {

	/**
	 * the number of hundreds in the ATM
	 */
	private int hundreds; //instance variable

	/**
	 * the number of fifties in the ATM
	 */
	private int fifties;

	/**
	 * the number of twenties in the ATM
	 */
	private int twenties;

	/**
	 * boolean to suspend all ATMs
	 */
	private static boolean suspend = false;

	/**
	 * default constructor
	 */
	public ATM() {
		hundreds = 0;
		fifties = 0;
		twenties = 0;
	}

	/***********************************************************************
	 * constructor to initialize instance variables with other ATM parameter
	 * @param other
	 ***********************************************************************/
	public ATM(ATM other) {
		this.hundreds = other.hundreds;
		this.fifties = other.fifties;
		this.twenties = other.twenties;
	}

	/****************************
	 *
	 * @return integer hundreds
	 ****************************/
	public int getHundreds() {
		return hundreds;
	}

//	/**
//	 * set this.hundreds to new integer
//	 * @param hundreds
//	 */
//	public void setHundreds(int hundreds) {
//		this.hundreds = hundreds;
//	}

	/**************************
	 *
	 * @return integer fifties
	 **************************/
	public int getFifties() {
		return fifties;
	}

//	/**
//	 * set this.fifties to new integer
//	 * @param fifties
//	 */
//	public void setFifties(int fifties) {
//		this.fifties = fifties;
//	}

	/**
	 *
	 * @return integer fifties
	 */
	public int getTwenties() {
		return twenties;
	}

//	/**
//	 * set this.twenties to new integer
//	 * @param twenties
//	 */
//	public void setTwenties(int twenties) {
//		this.twenties = twenties;
//	}

	/******************************************************************
	 *  A constructor that initializes the instance variables with
	 *  the parameters.
	 *
	 *
	 * @param hundries the initial number of hundreds in the ATM
	 * @param fifties the initial number of fifties in the ATM
	 * @param twenties the initial number of twenties in the ATM
	 * @throws IllegalArgumentException with negative parameters
	 *
	 *****************************************************************/
	public ATM(int hundries, int fifties, int twenties) {
		// disallow negative params
		if (hundries < 0 || fifties < 0 || twenties < 0)
			throw new IllegalArgumentException();

		this.hundreds = hundries;
		this.fifties = fifties;
		this.twenties = twenties;
	}

	private static int convertToDollars(ATM temp) {
		return (temp.hundreds * 100) + (temp.fifties * 50) +
				(temp.twenties * 20);
	}

	/**
	 * Method that returns true if this ATM object is the same as Other Object
	 *
	 * @param other
	 * @return true
	 */

	public boolean equals (Object other) {
		if (other != null) {
			if (other instanceof ATM) {
				ATM temp = (ATM) other;


				if (this.hundreds == temp.hundreds &&
						this.fifties == temp.fifties &&
						this.twenties == temp.twenties)
					return true;
				else
					return false;
			}
		}
		throw new IllegalArgumentException();
	}

	//method converts atm 1 and 2 to dollar amounts, then checks to see if values are equal
	public static boolean equals (ATM other1, ATM other2) {
		if (other2 == null || other1 == null) {
			throw new IllegalArgumentException();
		}
		return (convertToDollars(other1) == convertToDollars(other2));
	}

	public int compareTo (ATM other) {
		if (convertToDollars(this) > convertToDollars(other))
			return 1;
		else if (convertToDollars(this) < convertToDollars(other))
			return -1;
		else
			return 0;
	}

	public int compareTo (ATM other1, ATM other2) {
		if (convertToDollars(other1) > convertToDollars(other2))
			return 1;
		else if (convertToDollars(other1) < convertToDollars(other2))
			return -1;
		else
			return 0;
	}

	public void putIn(int hunderies, int fifties, int twenties) {
		if (suspend)
			return;

		// disallow negative ATM params
		if (hunderies < 0 || fifties < 0 || twenties < 0)
			throw new IllegalArgumentException();

		this.hundreds += hunderies;
		this.fifties += fifties;
		this.twenties += twenties;
	}

	public void putIn(ATM other) {
		this.hundreds += other.hundreds;
		this.fifties += other.fifties;
		this.twenties += other.twenties;
	}

	public void putIn(String amount) {
		int dollars = Integer.parseInt(amount);
		int hundreds = dollars / 100;
		dollars = dollars % 100;
	}

	public void takeOut(int hundreds, int fifties, int twenties) {
		if (suspend)
			return;

		if (hundreds < 0 || fifties < 0 || twenties < 0)
			throw new IllegalArgumentException();

		this.hundreds -= hundreds;
		this.fifties -= fifties;
		this.twenties -= twenties;
	}


	/**************************************************
	 * Subtracts ATM other param to the this.atm object
	 * @param other
	 *
	 **************************************************/
	public void takeOut(ATM other) {
		if ((this.hundreds < other.hundreds) || (this.fifties < other.fifties) || (this.twenties < other.twenties))
			throw new IllegalArgumentException();

		if (suspend)
			return;

		this.hundreds -= other.hundreds;
		this.fifties -= other.fifties;
		this.twenties -= other.twenties;
	}

	public ATM takeOut(int totalAmount) {
		if (suspend)
			return null;

		if (totalAmount < 0)
			throw new IllegalArgumentException();

		if (totalAmount <= (hundreds * 100 + fifties * 50 + twenties * 20)) {
			for (int a = hundreds; a >= 0; a--) {
				for (int b = fifties; b >=0; b--) {
					for (int c= twenties; c >=0; c--) {

						if ((a * 100 + b * 50 + c * 20) == totalAmount) {
							hundreds -= a;
							fifties -= b;
							twenties -= c;
							return new ATM(a, b, c);

						}
					}
				}
			}
		}
		throw new IllegalArgumentException();
	}

	public String toString() {
		String s = this.hundreds + " hundred dollar bill";
		String t = this.fifties + " fifty dollar bill";
		String u = this.twenties + " twenty dollar bill";

		if (hundreds != 1)
			s += "s";

		if (fifties != 1)
			t += "s";

		if (twenties != 1)
			u += "s";

		return s + ", " + t + ", " + u;
	}

	public void save(String fileName) {

		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}


		out.println(hundreds);
		out.println(fifties);
		out.println(twenties);
		out.close();
	}

	public void load(String fileName) {
		try {
			Scanner fileReader = new Scanner(new File(fileName));
			hundreds = fileReader.nextInt();
			fifties = fileReader.nextInt();
			twenties = fileReader.nextInt();

			System.out.println(hundreds);
			System.out.println(fifties);
			System.out.println(twenties);
		} catch (Exception error) {
			throw new IllegalArgumentException();
		}
	}

	public static void suspend (Boolean on){
		suspend = on;
	}

}