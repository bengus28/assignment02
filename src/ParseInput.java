import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ParseInput {

	/**
	 * HashMap key - Integer value representing word length value - ArrayList
	 * containing String[] arrays of word pairs
	 */
	private Map<Integer, ArrayList<String[]>> words = new HashMap<Integer, ArrayList<String[]>>();

	public ParseInput() {

	}

	public ParseInput(File filename) {
		parse(filename);
	}

	public void parse(File filename) {
		try {

			// Pass in location of diller.txt
			Scanner inFile = new Scanner(filename);

			while (inFile.hasNext()) {
				// Splits the line at a space character and creates String[] array
				String[] wordPair = inFile.nextLine().split("\\s+");

				// Gets the length of the first String in the array (should both be the same) - use as key in 'words'
				int wordLength = wordPair[1].length();

				// Check if wordLength key exists
				if (words.get(wordLength) == null) {
					// Create a new ArrayList of String[] arrays with key wordLength
					words.put(wordLength, new ArrayList<String[]>());
				}

				// Adds the String[] word pair to the ArrayList
				words.get(wordLength).add(wordPair);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public Map<Integer, ArrayList<String[]>> getWordPairMap() {
		return words;
	}
	
	public List<String[]> getWordPairs() {
		List<String[]> wordPairs = new ArrayList<String[]>();
		for (Integer wordLength : words.keySet()) {
			List<String[]> singleLengthWordPairs = words.get(wordLength);
			for (String[] wordPair : singleLengthWordPairs) {
				wordPairs.add(wordPair);
			}
		}
		return wordPairs;
	}
	
	public List<Integer> getWordLengths() {
		List<Integer> wordLengths = new ArrayList<Integer>(words.keySet());
		return wordLengths;
	}

	public String toString() {
		String output = "Word pairs sorted by length:";
			for (Integer key : words.keySet()) {
				ArrayList<String[]> wordPairs = words.get(key);
				// (ignore this comment) Don't use the key to get the value, use Map.Entry instead to save the lookup.
				output += "\n\t";
				output += key + " ";
				
				output += "[";
				
				// Without commas separating String[] arrays
//				for (String[] wordPair : wordPairs) {
//					output += Arrays.toString(wordPair);
//				}
				
				// With commas separating String[] arrays

				for (int i = 0; i < wordPairs.size(); i++) {
					output += Arrays.toString(wordPairs.get(i));
					if (i < (wordPairs.size() - 1)) {
						output += ", ";
					}
				}
				
				output += "]";
			}
		return output;
	}

}
