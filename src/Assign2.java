import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Assign2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String diller = "/Users/adam/Documents/School/School 11-12 Spring/COMP 2210/projects/assignment02/diller1.txt";
		String sowpods = "/Users/adam/Documents/School/School 11-12 Spring/COMP 2210/projects/assignment02/sowpods.txt";
		
		// HashMap containing word lengths
		Map<Integer, Boolean> wordLengths = new HashMap<Integer, Boolean>();
		
		// ArrayList or String arrays containing word pairs
		List<String[]> wordPairs = new ArrayList<String[]>();
		
		try {
			// Pass in location of diller.txt
			Scanner inFile = new Scanner(new File(diller));
			
			while (inFile.hasNext()) {
				// Splits the line at a space character and creates String array
				String[] wordPair = inFile.nextLine().split("\\s+");
				
				// Gets the length of the first String in the array (should both be the same) and adds to wordLength
				wordLengths.put(wordPair[1].length(), true);
				
				// Adds the word pair to wordPairs
				wordPairs.add(wordPair);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Prints the HashMap of word lengths
		System.out.println("Word Lengths:\n\t" + wordLengths);
		
		// Prints the word pairs
		System.out.println("\nWord Pairs:");
		for (String[] wordPair : wordPairs) {
			System.out.println("\t" + Arrays.toString(wordPair));
		}
		
		Lexicon lexicon = new Lexicon();
		lexicon.open(new File(sowpods));
		lexicon.parseFullDictionary(wordLengths);
		
		Map<Integer, ArrayList<String>> sorted = lexicon.getSorted();
		
		// Print
		System.out.println("\nSowPods:");
		for (Integer key : sorted.keySet()) {
			ArrayList<String> value = sorted.get(key);
			
			// Just so that we aren't displaying all of the words on this print
			String output = value.toString();
			if (output.length() > 50) {
				output = output.substring(0, 50);
			}
			
			System.out.println(key + " " + output);
			// Don't use the key to get the value, use Map.Entry instead to save
			// the lookup.
		}
		
		// System.out.println(wordLengths.get(2));
		
	}

}
