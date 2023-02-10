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
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.awt.geom.*;
import javax.swing.border.LineBorder;
import javax.imageio.*;
import java.awt.image.*;
public class Game extends JPanel implements ActionListener{
	
	JPanel keyboardPanel = new JPanel();
	JPanel wordPanel = new JPanel();
	JPanel errorPanel = new JPanel();
	JPanel displayMenuBtn = new JPanel();	
	ImageIcon bg; 
	JTextField hiddenWord;
	JLabel displayError = new JLabel();
	JButton keys[][] = new JButton[2][13];
	JButton menu = new JButton("Menu");
	GetWord myWord = new GetWord();
	ArrayList<String> words = new ArrayList<String>();
	
	char character = 'a';							
	public String word;
	public boolean repeat = false;
	public String updatedWord = "";
	public String hideWord = "";
	public int errorCount = 0;
	
	public Game(){
		super();
		menu.setBackground(Color.white);
		menu.setFont(menu.getFont().deriveFont(Font.BOLD, 20));
		menu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		word = myWord.nextWord();
		displayMenuBtn.setOpaque(false);
		errorPanel.setOpaque(false);
		wordPanel.setOpaque(false);
		bg = new ImageIcon("GameBG.jpg");
		for(int i = 0; i < word.length(); i++){ //adds '-' for how many characters are in the word, then displayed in JTextField
			hideWord += "-";
		}
		displayError.setText("Errors: " + errorCount); //display number of errors
		hiddenWord = new JTextField(word.length()); //size of JTextfield depends on how big the word is
		hiddenWord.setOpaque(false);
		hiddenWord.setText(hideWord);
		hiddenWord.setEditable(false);
		hiddenWord.setHorizontalAlignment(JTextField.CENTER);
		hiddenWord.setBorder(new LineBorder(Color.BLACK, 0)); // Removes borders on JTextField
		hiddenWord.setFont(hiddenWord.getFont().deriveFont(Font.BOLD, 70)); //font size for hidden word
		keyboardPanel.setLayout(new GridLayout(keys.length,keys[0].length));
		//Used to display letter at the bottom
		for(int row = 0; row < keys.length; row++){
			for(int col = 0; col < keys[0].length; col++){
				keys[row][col] = new JButton(character + "");
				character++;
				keys[row][col].setHorizontalAlignment(JButton.CENTER);
				keys[row][col].setFont(keys[row][col].getFont().deriveFont(Font.BOLD, 40)); //font size for letter
				keys[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK, 4)); //Increase border thickness
				keys[row][col].setBackground(Color.white); //button color is white
				keyboardPanel.add(keys[row][col]); //buttons added to panel
				keys[row][col].addActionListener(this); //functionality for letters
			}
		}
		errorPanel.setLayout(new FlowLayout());
		errorPanel.add(displayError);
		displayMenuBtn.setLayout(new FlowLayout());
		displayMenuBtn.add(menu);
		wordPanel.setLayout(new BoxLayout(wordPanel, BoxLayout.Y_AXIS));
		wordPanel.add(hiddenWord);
		this.setLayout(new BorderLayout());
		this.add(displayMenuBtn, BorderLayout.EAST);
		this.add(errorPanel, BorderLayout.WEST);
		this.add(wordPanel, BorderLayout.CENTER);
		this.add(keyboardPanel, BorderLayout.SOUTH);
		menu.addActionListener(this);
		System.out.println(word);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == menu){ //if menu button is pressed
			Main.myWindow.setVisible(true); //open menu window
			Menu.gameWindow.dispose(); //close game window
			reset();
			menu.setEnabled(true);
			menu.setBackground(Color.white);
			errorCount--;
		}
		JButton b = (JButton) e.getSource();
		b.setOpaque(true);
		for(int i = 0; i < word.length(); i++){ //loops repeats for num of characters in the word
			if(b.getText().equals(Character.toString(word.charAt(i)))){ //if input equals character in the word
				if(!repeat){ //boolean goes through every letter in word
					updatedWord += b.getText(); //if input appears, it will be added to updatedWord
				}
				else{
					updatedWord = updatedWord.substring(0, i) + b.getText() + updatedWord.substring(i + 1, updatedWord.length());
				}
			}
			else{ 
				if(!repeat){
					updatedWord += "-"; //incorrect guess will add hyphens
				}
			}
		}
		hideWord = updatedWord;
		repeat = true;
		hiddenWord.setText(updatedWord); //displays updatedWord in JTextField
		if(hideWord.contains(b.getText())){ //if guessed correctly,
			b.setBackground(Color.GREEN); //letter will turn green,
			b.setEnabled(false); //and can't be pressed again
		}
		else{ //if guessed incorrectly, letter will turn red and can't be pressed again
			errorCount++; //errorCount increases,
			displayError.setText("Errors: " + errorCount);//error JLabel gets updated,
			b.setBackground(Color.RED);//letter turns red,
			b.setEnabled(false);//can't be pressed again.
			menu.setEnabled(true);
			menu.setBackground(Color.white);
		}
		if(errorCount == 6){//game over
			JOptionPane.showMessageDialog(null, "You lost! the word was '" + word + "' .", "GAME OVER", JOptionPane.ERROR_MESSAGE);
			reset();
			Main.myWindow.setVisible(true);
			Menu.gameWindow.setVisible(false);
		}
		else if(hideWord.equals(word)){	//player wins
			JOptionPane.showMessageDialog(null, "You won!", "YOU WIN!", JOptionPane.INFORMATION_MESSAGE);
			reset();
			Main.myWindow.setVisible(true);
			Menu.gameWindow.dispose();
		}
		System.out.println(hideWord.contains(b.getText()));
		repaint();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bg.getImage(), 0, 0, 1495, 700, this); //Game background
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
		g.setColor(Color.white);
		g.drawLine(400, 175, 400, 450);
		//Body part added depending on number of errors
		if(errorCount >= 1){
			//head
			g.fillOval(462, 210, 75, 75);
		}
		if(errorCount >= 2){
			//torso
			g.drawLine(500, 285, 500, 375);
		}
		if(errorCount >= 3){
			//right
			g.drawLine(500, 285, 550, 375);	
		}
		if(errorCount >= 4){
			//left
			g.drawLine(500, 285, 450, 375);
		}
		if(errorCount >= 5){
			//right leg
			g.drawLine(500, 380, 550, 425);
		}
		if(errorCount >= 6){
			//left leg
			g.drawLine(500, 380, 450, 425);
		}
		g.drawLine(400, 175, 500, 175);
		g.drawLine(400, 215, 450, 175);
		g.drawLine(370, 450, 475, 450);
		g.drawLine(500, 175, 500, 210);
	}
	//Method used to create a new word when player has lost, won, or pressed menu button
	public void reset(){
		errorCount = 0;
		displayError.setText("Errors: " + errorCount);
		repeat = false;
		updatedWord = "";
		hideWord = "";
		word = myWord.nextWord();
		for(int i = 0; i < word.length(); i++){
			hideWord += "-";
		}
		hiddenWord.setText(hideWord);
		for(int i = 0; i < keys.length; i++){
			for(int j = 0; j < keys[0].length; j++){
				keys[i][j].setEnabled(true);
				keys[i][j].setBackground(Color.white);
			}
		}
		System.out.println(word);
		menu.setEnabled(true);
		menu.setBackground(Color.white);
	}
}