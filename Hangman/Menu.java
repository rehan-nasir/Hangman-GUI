/*
Rehan Nasir
ICS4U1
Ms. Strelkovska
2021-06-22
Final Project
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class Menu extends JPanel implements ActionListener{
	
	JButton b1, b2, b3;
	ImageIcon title, bg;
	JLabel pic1;
	static JFrame gameWindow = new JFrame("Game");
	static Game game = new Game();
	
	public Menu(){ 
		b1 = new JButton("Play");
		b2 = new JButton("Instructions");
		b3 = new JButton("Exit");
		title = new ImageIcon("HangmanTitle.png");
		bg = new ImageIcon("MenuBG.png");
		pic1 = new JLabel(title);
		
		//Alignment for components
		pic1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		b1.setAlignmentX(JComponent.CENTER_ALIGNMENT);	
		b2.setAlignmentX(JComponent.CENTER_ALIGNMENT);	
		b3.setAlignmentX(JComponent.CENTER_ALIGNMENT);	
		//Formatting of components
		b1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
		b2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
		b3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
		b1.setFont(b1.getFont().deriveFont(Font.BOLD, 40));
		b2.setFont(b2.getFont().deriveFont(Font.BOLD, 40));
		b3.setFont(b3.getFont().deriveFont(Font.BOLD, 40));
		//Applying components
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(pic1);
		this.add(Box.createRigidArea(new Dimension(10, 45)));
		this.add(b1);
		this.add(Box.createRigidArea(new Dimension(10, 45)));
		this.add(b2);
		this.add(Box.createRigidArea(new Dimension(10, 45)));
		this.add(b3);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);		
		gameWindow.add(game);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setSize(1495, 700);
		gameWindow.setResizable(false);
		gameWindow.setResizable(false);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == b1){ // If play button is pressed, display game window and dispose menu
			gameWindow.setVisible(true);
			Main.myWindow.dispose();
		}
		if(e.getSource() == b2){ // If instructions button is pressed, display instructions
			JOptionPane.showMessageDialog(null, "INSTRUCTIONS" + "\n " + 
												"A random word will be given to you, and your job is to guess it." + "\n" +
												"You will have to guess letter by letter. Be careful though, as you" + "\n" +
												"can only guess incorrectly 6 times." + "\n" + "\n"+
												"HOW TO PLAY" + "\n" +
												"You will see a set of letters at the bottom of the window. Use" + "\n"+
												"your mouse to select a letter. If it turns green, you guessed" + "\n" +
												"correctly. If it turns red, your guess was incorrect.", "Instructions", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource() == b3){ //If exit is pressed, close program
			System.exit(0);
		}
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bg.getImage(), 0, 0, 600, 700, null); //background image
	}
}