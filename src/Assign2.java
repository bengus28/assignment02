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
		
		String diller = "diller1.txt";
		String sowpods = "sowpods.txt";
		
		// HashMap containing word lengths
		Map<Integer, ArrayList<String[]>> words = new HashMap<Integer, ArrayList<String[]>>();
		
		try {
			// Pass in location of diller.txt
			Scanner inFile = new Scanner(new File(diller));
			
			while (inFile.hasNext()) {
				// Splits the line at a space character and creates String array
				String[] wordPair = inFile.nextLine().split("\\s+");
				
				// Gets the length of the first String in the array (should both be the same) - used as key
				int wordLength = wordPair[1].length();
				
				if (words.get(wordLength) == null) { //gets the value for an id)
					words.put(wordLength, new ArrayList<String[]>()); //no ArrayList assigned, create new ArrayList
				}
				words.get(wordLength).add(wordPair); //adds value to list.
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Prints the HashMap of word lengths	
		for (Integer key : words.keySet()) {
			ArrayList<String[]> value = words.get(key);
			
			// Just so that we aren't displaying all of the words on this print
			
			String output = "";
			for (String[] wordPair : value) {
				output += Arrays.toString(wordPair);
			}

			System.out.println(key + " " + output);
			// Don't use the key to get the value, use Map.Entry instead to save
			// the lookup.
		}
		
		Lexicon lexicon = new Lexicon();
		lexicon.open(new File(sowpods));
				
		lexicon.parseFullDictionary(new ArrayList<Integer>(words.keySet()));
		
		Map<Integer, ArrayList<String>> sorted = lexicon.getSorted();
		
		// Print sorted dictionary
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
				
	}

}
