import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Lexicon implements ILexicon {

	private Scanner inFile;

	private boolean isSorted = false;

	// Unsorted dictionary
	List<String> dictionary = new ArrayList<String>();

	// Sorted dictionary
	Map<Integer, ArrayList<String>> sortedDictionary = new HashMap<Integer, ArrayList<String>>();

	@Override
	public void open(File filename) {
		try {
			inFile = new Scanner(filename);
			while (inFile.hasNext())
				dictionary.add(inFile.nextLine());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sortDictionary(List<Integer> wordLengths) {
		for (String word : dictionary) {
			int wordLength = word.length();

			// Check to see if we care about this word
			if (wordLengths.contains(wordLength)) {

				// Check if wordLength key exists
				if (sortedDictionary.get(wordLength) == null) {
					// Create new ArrayList of Strings with key wordLength
					sortedDictionary.put(wordLength, new ArrayList<String>());
				}

				// Add value to list.
				sortedDictionary.get(wordLength).add(word);
			}
		}
		isSorted = true;
	}

	public Map<Integer, ArrayList<String>> getSorted() {
		return sortedDictionary;
	}
	
//	public List<String> getOneWordLength(int wordLength) {
//		return sortedDictionary.get(wordLength);
//	}

	public List<String> getDictionary() {
		return dictionary;
	}

	@Override
	public void close() {
		inFile.close();
	}

	@Override
	public boolean isWord(String str) {
		if (dictionary.contains(str.toUpperCase()))
			return true;
		return false;
	}

	@Override
	public boolean isPrefix(String str) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<String> wordsOneOff(String str) {
		int wordLength = str.length();
		String regex = generateRegex(str);
		if (isSorted && sortedDictionary.get(wordLength) != null) {
			return findMatchingWords(str, regex, sortedDictionary.get(wordLength));
		}
		return findMatchingWords(str, regex, dictionary);
	}

	public List<String> findMatchingWords(String originalWord, String regex, List<String> words) {
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

	@SuppressWarnings("rawtypes")
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public String generateRegex(String str) {
		StringBuilder sb = new StringBuilder("(");
		for (int i = 1; i <= str.length(); i++)
			sb.append(str.subSequence(0, i - 1)).append("[a-z]").append(str.subSequence(i, str.length())).append("|");
		sb.replace(sb.length() - 1, sb.length(), ")");
		System.out.println("Generated regex: " + sb);
		return sb.toString().toUpperCase();
	}

	public String toString() {
		if (isSorted) {
			int n = 10; // Number of items in each group to print
			String output = "Sowpods: (showing first " + n + " items for each word length)";
			for (Integer key : sortedDictionary.keySet()) {
				ArrayList<String> dictionarySubgroup = sortedDictionary.get(key);

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
