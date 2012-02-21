import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dodgson {

	private final int FROM = 0, TO = 1;
	
	public Dodgson() {
		
	}
	
	public ArrayList<String> findPath(String[] wordPair, Lexicon lexicon) {
		String startWord = wordPair[FROM];
		String endWord = wordPair[TO];
		
		int wordLength = startWord.length();
		
		List<String> dictionary = lexicon.getSorted().get(wordLength);
		
		String printOutput = "";
		printOutput += "Dodgson Sequence";
		printOutput += "\n\tStart word: " + startWord;
		printOutput += "\n\tEnd word: " + endWord;
		// Print first n elements of each dictionary subgroup
		int n = 10;
		printOutput += "\n\tFirst " + n + " Words of same length from dict: ";
		printOutput += "[";
		for (int i = 0; i < n; i++) {
			printOutput += dictionary.get(i);
			printOutput += i < (n - 1) ? ", " : "";
		}
		printOutput += (n < dictionary.size()) ? ", ..." : "";
		printOutput += "]";
		
		System.out.println(printOutput);
		
		return null;
	}
}
