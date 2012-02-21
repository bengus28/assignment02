import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Lexicon implements ILexicon {

	private Scanner inFile;
	
	Map<Integer, ArrayList<String>> sorted = new HashMap<Integer, ArrayList<String>>();
	
	@Override
	public void open(File filename) {
		// TODO Auto-generated method stub
		try {
			inFile = new Scanner(filename);
		} catch (FileNotFoundException e) {
			
		}
	}
	
	public void sort(Map<Integer, Boolean> wordLengths) {
		while (inFile.hasNext()) {
			String word = inFile.nextLine();
			int length = word.length();
			if (wordLengths.get(length) != null) {
				if (sorted.get(length) == null) { //gets the value for an id)
					sorted.put(length, new ArrayList<String>()); //no ArrayList assigned, create new ArrayList
				}
					sorted.get(length).add(word); //adds value to list.
			}
		}
	}
	
	public Map<Integer, ArrayList<String>> getSorted() {
		return sorted;
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
