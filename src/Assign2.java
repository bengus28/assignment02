import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Assign2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		File diller = new File("diller1.txt");
		File sowpods = new File("sowpods.txt");
		
		/**
		 * HashMap
		 * key - Integer value representing word length
		 * value - ArrayList containing String[] arrays of word pairs
		 */
		Map<Integer, ArrayList<String[]>> words = new HashMap<Integer, ArrayList<String[]>>();
		
		try {
			
			// Pass in location of diller.txt
			Scanner inFile = new Scanner(diller);
			
			while (inFile.hasNext()) {
				// Splits the line at a space character and creates String[] array
				String[] wordPair = inFile.nextLine().split("\\s+");
				
				// Gets the length of the first String in the array (should both be the same) - use as key in 'words'
				int wordLength = wordPair[1].length();
				
				// Create a new ArrayList of String[] arrays if one does not already exist
				if (words.get(wordLength) == null) {
					words.put(wordLength, new ArrayList<String[]>());
				}
				
				// Adds the String[] word pair to the ArrayList
				words.get(wordLength).add(wordPair);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Print the HashMap of word lengths	
		System.out.println("Word pairs sorted by length:");
		for (Integer key : words.keySet()) {
			ArrayList<String[]> wordPairs = words.get(key);
						
			String output = "\t";
				output += key + " ";
				for (String[] wordPair : wordPairs) {
					output += Arrays.toString(wordPair);
				}
			System.out.println(output);
			
			// (ignore this line) Don't use the key to get the value, use Map.Entry instead to save the lookup.
		}
		
		Lexicon lexicon = new Lexicon();
		lexicon.open(sowpods);
		lexicon.parseDictionary(new ArrayList<Integer>(words.keySet()));
		
		Map<Integer, ArrayList<String>> dictionary = lexicon.getSorted();
		
		// Print the sorted dictionary
		System.out.println("\nSowpods:");
		for (Integer key : dictionary.keySet()) {
			ArrayList<String> dictionarySubgroup = dictionary.get(key);
			
			String output = "\t";
				output += key;
				output += " [";
				// Print first n elements of each dictionary subgroup
				int n = 10;
				for (int i = 0; i < n; i++) {
					output += dictionarySubgroup.get(i);
					output += i < (n - 1) ? ", " : "";
				}
				output += "]";
			System.out.println(output);
		}
				
	}

}
