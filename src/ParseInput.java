import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Parses an input file of word pairs.
 * 
 * @author Adam Bulgatz and Ben Gustafson
 *
 */
public class ParseInput {

	/**
	 * <code>START_WORD_INDEX</code> - Location of the start word in the word pair <code>String[]</code> array.<br />
	 * <code>END_WORD_INDEX</code> - Location of the end word in the word pair <code>String[]</code> array.
	 */
	public static final int START_WORD_INDEX = 0, END_WORD_INDEX = 1;
	
	/**
	 * <h1>HashMap</h1>
	 * <ul>
	 * 	<li><strong>Key</strong> - Integer value representing word length.</li>
	 * 	<li><strong>Value</strong> - <code>ArrayList</code> containing <code>String[]</code> arrays of word pairs.</li>
	 * </ul>
	 */
	private Map<Integer, ArrayList<String[]>> words = new HashMap<Integer, ArrayList<String[]>>();

	
	public ParseInput() {

	}

	/**
	 * @param filename - path to input file
	 */
	public ParseInput(File filename) {
		parse(filename);
	}

	/**
	 * @param filename - path to input file
	 */
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
	
	/**
	 * @return HashMap of words
	 */
	public Map<Integer, ArrayList<String[]>> getWordPairMap() {
		return words;
	}
	
	/**
	 * @return Word pairs in a List of String arrays
	 */
	public List<String[]> getWordPairs() {
		
		// Create List of String[] arrays to contain all word pairs
		List<String[]> wordPairs = new ArrayList<String[]>();
		
		/* Get the keys (representing word lengths) from the words
		 * HashMap. For each word length key, get the associated List
		 * of Strings[] arrays. Add each String[] array to wordPairs.
		 */
		for (Integer wordLength : words.keySet()) {
			List<String[]> singleLengthWordPairs = words.get(wordLength);
			for (String[] wordPair : singleLengthWordPairs) {
				wordPairs.add(wordPair);
			}
		}
		return wordPairs;
	}
	
	/**
	 * @return Word lengths in a List of Integers
	 */
	public List<Integer> getWordLengths() {
		List<Integer> wordLengths = new ArrayList<Integer>(words.keySet());
		return wordLengths;
	}

	/**
	 * @return String based on HashMap
	 */
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
