import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Lexicon implements ILexicon {

	private Scanner inFile;
	
	// Sorted dictionary
	Map<Integer, ArrayList<String>> dictionary = new HashMap<Integer, ArrayList<String>>();
	
	@Override
	public void open(File filename) {
		try {
			inFile = new Scanner(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void parseDictionary(ArrayList<Integer> wordLengths) {
		while (inFile.hasNext()) {
			String word = inFile.nextLine();
			int wordLength = word.length();
			
			// Check to see if we care about this word
			if (wordLengths.contains(wordLength)) {
				
				// Check if wordLength key exists
				if (dictionary.get(wordLength) == null) {
					// Create new ArrayList of Strings with key wordLength
					dictionary.put(wordLength, new ArrayList<String>()); 
				}
				
				// Add value to list.
				dictionary.get(wordLength).add(word); 
			}
		}
	}
	
	public Map<Integer, ArrayList<String>> getSorted() {
		return dictionary;
	}

	@Override
	public void close() {
		inFile.close();
	}

	@Override
	public boolean isWord(String str) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPrefix(String str) {
		// TODO Auto-generated method stub
		return false;
	}
	 
	public String[] oneOff(String searchTerm)
	{
		String[] results = null;
		return results;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		int n = 10; // Number of items in each group to print
		
		String output = "Sowpods: (showing first " + n + " items for each word length)";
		
		for (Integer key : dictionary.keySet()) {
			ArrayList<String> dictionarySubgroup = dictionary.get(key);
			
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

}
