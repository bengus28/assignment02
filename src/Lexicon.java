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
		// TODO Auto-generated method stub
		try {
			inFile = new Scanner(filename);
		} catch (FileNotFoundException e) {
			
		}
	}
	
	public void parseDictionary(ArrayList<Integer> wordLengths) {
		while (inFile.hasNext()) {
			String word = inFile.nextLine();
			int wordLength = word.length();
			if (wordLengths.contains(wordLength)) {
				if (dictionary.get(wordLength) == null) { //gets the value for an id)
					dictionary.put(wordLength, new ArrayList<String>()); //no ArrayList assigned, create new ArrayList
				}
					dictionary.get(wordLength).add(word); //adds value to list.
			}
		}
	}
	
	public Map<Integer, ArrayList<String>> getSorted() {
		return dictionary;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

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

}
