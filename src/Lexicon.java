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

	// Full word list
	List<String> wordList = new ArrayList<String>();

	// Pruned word list
	Map<Integer, ArrayList<String>> prunedWordList = new HashMap<Integer, ArrayList<String>>();

	@Override
	public void open(File filename) {
		try {
			inFile = new Scanner(filename);
			while (inFile.hasNext())
				wordList.add(inFile.nextLine().toUpperCase());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Prune the word list by keeping only words of a certain length.
	 * 
	 * @param wordLengths - List of Integers containing word lengths.
	 */
	public void prune(List<Integer> wordLengths) {
		for (String word : wordList) {
			int wordLength = word.length();

			// Check to see if we care about this word
			if (wordLengths.contains(wordLength)) {

				// Check if wordLength key exists
				if (prunedWordList.get(wordLength) == null) {
					// Create new ArrayList of Strings with key wordLength
					prunedWordList.put(wordLength, new ArrayList<String>());
				}

				// Add value to list.
				prunedWordList.get(wordLength).add(word);
			}
		}
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
		if (wordList.contains(str.toUpperCase()))
			return true;
		return false;
	}

	@Override
	public boolean isPrefix(String str) {
		StringBuilder sb = new StringBuilder("\\b");
		sb.append(str.toUpperCase() + "[A-Z]");
		for (String word : wordList) {
			if (Pattern.matches(sb.toString(), word)) {
				return true;
			}
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
		int wordLength = str.length();
		String regex = wordsOneOffRegex(str);
		if (isPruned && prunedWordList.get(wordLength) != null) {
			return wordsOneOff(str, regex, prunedWordList.get(wordLength));
		}
		return wordsOneOff(str, regex, wordList);
	}

	/**
	 * Helper method for public wordsOneOff that does the actual string matching.
	 * This was created so that there wouldn't be duplicate code in the public
	 * wordsOneOff after checking to see if the dictionary has been sorted.
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
	 * @return a String of the regular expression. If input is "the", generated
	 * expression will be "([A-Z]HE|T[A-Z]E|TH[A-Z])".
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
	 * If the word list has been pruned, return the pruned word list, else return the full dictionary.
	 * 
	 * @return A String
	 */
	public String toString() {
		if (isPruned) {
			int n = 10; // Number of items in each group to print
			String output = "Sowpods: (showing first " + n + " items for each word length)";
			for (Integer key : prunedWordList.keySet()) {
				ArrayList<String> dictionarySubgroup = prunedWordList.get(key);

				output += "\n\t";
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
			return output;
		}
		return this.toString();
	}

}
