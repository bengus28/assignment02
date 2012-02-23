import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Defines operations on a lexicon - a collection of strings that 
 * are accepted as words in the English language.
 * 
 * @author Adam Bulgatz and Ben Gustafson
 *
 */
public class Lexicon implements ILexicon {

	private Scanner inFile;

	private boolean isPruned = false;

	/**
	 * Full word list
	 */
	List<String> wordList = new ArrayList<String>();

	/**
	 * Pruned word list
	 */
	Map<Integer, ArrayList<String>> prunedWordList = new HashMap<Integer, ArrayList<String>>();

	@Override
	public void open(File filename) {
		try {
			
			// Instantiate inFile scanner with filename
			inFile = new Scanner(filename);
			
			// Make each word in the file upper-case and add to the wordList
			while (inFile.hasNext())
				wordList.add(inFile.nextLine().toUpperCase());
			
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			System.out.println("Invalid file location");
		}
	}

	/**
	 * Prune the word list by keeping only words of a certain length.
	 * 
	 * @param wordLengths - List of Integers containing word lengths.
	 */
	public void prune(List<Integer> wordLengths) {
		
		// For each word in the wordList
		for (String word : wordList) {
			
			// Get the word length
			int wordLength = word.length();

			// Check to see if we care about this word
			if (wordLengths.contains(wordLength)) {

				// Check if a key already exists for wordLength
				if (prunedWordList.get(wordLength) == null) {
					// If it doesn't, create a new ArrayList of Strings with key wordLength
					prunedWordList.put(wordLength, new ArrayList<String>());
				}

				// Add the word to the pruned list with key wordLength
				prunedWordList.get(wordLength).add(word);
			}
		}
		
		// Set isPruned to true
		isPruned = true;
	}

	/**
	 * Get the pruned word list.
	 * 
	 * @return A HashMap with word lengths as keys if the word list has been pruned,
	 * otherwise returns null.
	 */
	public Map<Integer, ArrayList<String>> getPrunedWordList() {
		if (isPruned)
			return prunedWordList;
		else
			return null;
	}

	/**
	 * Get the full word list.
	 * 
	 * @return A List of Strings.
	 */
	public List<String> getWordList() {
		return wordList;
	}

	@Override
	public void close() {
		inFile.close();
	}

	@Override
	public boolean isWord(String str) {
		
		// Get the word length
		int wordLength = str.length();
		
		// Use the pruned word list if we can
		if (canUsePruned(wordLength)) {
			if (prunedWordList.get(wordLength).contains(str.toUpperCase()))
				return true;
		} else if (wordList.contains(str.toUpperCase()))
			return true;
		return false;
	}
	
	/**
	 * Validates a word pair
	 * 
	 * @param wordPair
	 * @return A List of Strings. Null if the word pair is valid.
	 * Otherwise contains the invalid word(s).
	 */
	public List<String> isValidWordPair(String[] wordPair) {
		// Get the start and end words from the String[] array
		String startWord = wordPair[ParseInput.START_WORD_INDEX];
		String endWord = wordPair[ParseInput.END_WORD_INDEX];
		
		// Check if startWord and endWord are valid words
		boolean isStartWordValid = isWord(startWord);
		boolean isEndWordValid = isWord(endWord);
		
		// Define a null List of Strings
		List<String> output = null;
		
		/*
		 * If either the startWord or endWord are not valid words,
		 * instantiate the List of Strings and add the non-valid word(s).
		 */
		if (!isStartWordValid || !isEndWordValid) {
			output = new ArrayList<String>();
			if (!isStartWordValid)
				output.add(startWord);
			if (!isEndWordValid)
				output.add(endWord);
		}
		
		return output;
	}

	@Override
	public boolean isPrefix(String str) {
		/*
		 * Build a regular expression to match the input string to the
		 * beginning of a word. Note: '\b' is a regex word boundary.
		 * The final regex should look like '\bINPUT[A-Z]' without the quotes.
		 * the '[A-Z]' is necessary to make sure that str is a prefix, not
		 * an entire word. str has been converted to upper-case to match
		 * the word list.
		 */
		String regex = "\\b" + str.toUpperCase() + "[A-Z]";
		
		// Perform the search on each word in the full word list
		for (String word : wordList) {
			if (Pattern.matches(regex, word)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks if the pruned list can be used in a search.
	 * 
	 * @param wordLength - the length of the word to check against.
	 * @return true if the word list has been pruned and the pruned
	 * list contains words of wordLength.
	 */
	private boolean canUsePruned(int wordLength) {
		if (isPruned && prunedWordList.get(wordLength) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Get a list of words with a one character difference from an input word.
	 * 
	 * @param str - input word.
	 * @return List of Strings.
	 */
	public List<String> wordsOneOff(String str) {
		
		// If input is not a valid word, return an empty List of Strings
		if (!isWord(str))
			return new ArrayList<String>();
		
		// Get the word length
		int wordLength = str.length();
		
		// Generate regex for the word
		String regex = wordsOneOffRegex(str);
		
		// Use the pruned list if we can
		if (canUsePruned(wordLength)) {
			return wordsOneOff(str, regex, prunedWordList.get(wordLength));
		}
		return wordsOneOff(str, regex, wordList);
	}

	/**
	 * Helper method for public wordsOneOff that does the actual string matching.
	 * This was created so that there wouldn't be duplicate code in the public
	 * wordsOneOff after checking to see if the dictionary has been pruned.
	 * 
	 * @param originalWord - the original word.
	 * @param regex - the regular expression generated from the original word.
	 * @param words - the list of words to search.
	 * @return A List or words
	 */
	private List<String> wordsOneOff(String originalWord, String regex, List<String> words) {
		List<String> matchingWords = new ArrayList<String>();
		for (String word : words) {
			if (Pattern.matches(regex, word)) {
				matchingWords.add(word);
			}
		}
		// Remove original word from return
		int originalWordIndex = matchingWords.indexOf(originalWord.toUpperCase());
		matchingWords.remove(originalWordIndex);
		return matchingWords;
	}
	
	/**
	 * Helper method for wordsOneOff that generates the regular expression
	 * to match against each word in the word list.
	 * 
	 * @param str - the base word for the generated regular expression.
	 * @return A String of the regular expression. If input is "the", generated
	 * expression will be <nobr><code>([A-Z]HE|T[A-Z]E|TH[A-Z])</code></nobr>.
	 */
	private String wordsOneOffRegex(String str) {
		StringBuilder sb = new StringBuilder("(");
		for (int i = 1; i <= str.length(); i++)
			sb.append(str.subSequence(0, i - 1)).append("[a-z]").append(str.subSequence(i, str.length())).append("|");
		sb.replace(sb.length() - 1, sb.length(), ")");
//		System.out.println("Generated regex: " + sb);
		return sb.toString().toUpperCase();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the pruned word list (if the word list has been pruned) and
	 * the full word list as a String.
	 * 
	 * @return A String (duh)
	 */
	public String toString() {
		// Number of items in each list to print
		int n = 10;
		
		String output = "The word list ";
		output += isPruned ? "has been pruned:" : "has not been pruned:";
		
		if (isPruned) {
			output += "\n\tPruned word list: (showing first " + n + " items for each word length)";
			for (Integer key : prunedWordList.keySet()) {
				ArrayList<String> dictionarySubgroup = prunedWordList.get(key);

				output += "\n\t\t";
				output += key;
				output += " [";
				// Print first n elements of each dictionary subgroup
				for (int i = 0; i < n; i++) {
					output += dictionarySubgroup.get(i);
					output += i < (n - 1) ? ", " : "";
				}
				output += (n < dictionarySubgroup.size()) ? ", ..." : "";
				output += "]";
			}
		}
		output += "\n\tFull word list: (showing first " + n + " items)";
		output += "\n\t\t";
		output += "[";
		for (int i = 0; i < n; i++) {
			output += wordList.get(i);
			output += i < (n - 1) ? ", " : "";
		}
		output += (n < wordList.size()) ? ", ..." : "";
		output += "]";
		return output;
	}

}
