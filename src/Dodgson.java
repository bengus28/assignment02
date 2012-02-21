import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dodgson {

	private final int FROM = 0, TO = 1;
	private String from, to;

	Map<String, Map<String, ArrayList<String>>> searchTree = new HashMap<String, Map<String, ArrayList<String>>>();

	public Dodgson() {
		
	}

	public void findFromDiller(Map<Integer, ArrayList<String[]>> words, Map<Integer, ArrayList<String>> dictionary) {
		for (Integer wordLength : words.keySet()) {
			ArrayList<String[]> wordPairs = words.get(wordLength);
			for (String[] wordPair : wordPairs) {
				findFromStringArray(wordPair);
				
				
				
			}
		
		}
	}
	
	public ArrayList<String> findFromStringArray(String[] wordPair) {
		from = wordPair[FROM];
		to = wordPair[TO];
		
		
		
		return null;
	}
}
