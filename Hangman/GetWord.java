/*
Rehan Nasir
ICS4U1
Ms. Strelkovska
2021-06-22
Final Project
*/
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class GetWord{
	static ArrayList<String> words = new ArrayList<String>();
	public static String line;
	
	public GetWord(){
	}
	public void addWord(){
		Scanner file = null;
		try{
			file = new Scanner(new File("Words.txt"));
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		catch(Exception e){
				System.out.println("something else");
		}
		while(file.hasNext()){ //reads all lines from the txt file
			line = file.nextLine();
			words.add(line); //adds the words to the arraylist
		}
	}
	public String nextWord(){
		if(words.size() == 0){ //if there are no more elements, arraylist adds all of the words again
			addWord();
		}
		int num = (int)(Math.random()* words.size()); //select random word
		return words.remove(num); //you won't get the same word twice
	}
}
