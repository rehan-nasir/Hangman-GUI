/*
Rehan Nasir
ICS4U1
Ms. Strelkovska
2021-06-22
Final Project
*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main{
	
	static JFrame myWindow = new JFrame("Hangman - Menu");

	public static void main(String[]args){
		Container c = myWindow.getContentPane();
		c.setLayout(new BorderLayout());
		Menu menu = new Menu();
		c.add(menu, BorderLayout.CENTER);
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myWindow.setVisible(true); 
		myWindow.setResizable(false);
		myWindow.setSize(600, 700);
	}
}