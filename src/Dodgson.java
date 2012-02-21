import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dodgson {

	private final int FROM = 0, TO = 1;
	
	private final String RELATED = "related", BREADCRUMBS = "breadcrumbs";
	
	Map<String, Map<String, List<String>>> searchTree = new HashMap<String, Map<String, List<String>>>();
	
	public Dodgson() {
		
	}
	
	public List<String> findPath(String[] wordPair, Lexicon lexicon) {
		String startWord = wordPair[FROM];
		String endWord = wordPair[TO];
		
		int wordLength = startWord.length();
		
		List<String> dictionary = lexicon.getSorted().get(wordLength);
		
		// Print
			String printOutput = "";
			printOutput += "Dodgson Sequence";
			printOutput += "\n\tStart word: " + startWord;
			printOutput += "\n\tEnd word: " + endWord;
			// Print first n elements of each dictionary subgroup
			int n = 10;
			printOutput += "\n\tFirst " + n + " words from dictionary of same length as word pair:";
			printOutput += "\n\t\t[";
			for (int i = 0; i < n; i++) {
				printOutput += dictionary.get(i);
				printOutput += i < (n - 1) ? ", " : "";
			}
			printOutput += (n < dictionary.size()) ? ", ..." : "";
			printOutput += "]";
			
			System.out.println(printOutput);
		// End Print
		
		searchTree.put(startWord, new HashMap<String, List<String>>());
		searchTree.get(startWord).put(RELATED, lexicon.wordsOneOff(startWord));
		searchTree.get(startWord).put(BREADCRUMBS, new ArrayList<String>());
		searchTree.get(startWord).get(BREADCRUMBS).add(startWord);
		
		for (String parentWord : searchTree.keySet()) {
			for (String relatedWord : searchTree.get(parentWord).get(RELATED)) {
				if (relatedWord.equals(endWord)) {
					List<String> output = searchTree.get(parentWord).get(BREADCRUMBS);
					output.add(relatedWord);
					return output;
				}
				searchTree.put(relatedWord, new HashMap<String, List<String>>());
				searchTree.get(relatedWord).put(RELATED, lexicon.wordsOneOff(relatedWord));
				searchTree.get(relatedWord).put(BREADCRUMBS, searchTree.get(parentWord).get(BREADCRUMBS));
				searchTree.get(relatedWord).get(BREADCRUMBS).add(relatedWord);
			}
		}
		return null;
	}
}
