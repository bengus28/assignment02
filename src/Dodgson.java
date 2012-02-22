import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.List;
import java.util.Map;

public class Dodgson {

	private final int FROM = 0, TO = 1;

	private final String RELATED = "related", BREADCRUMBS = "breadcrumbs";

	public Dodgson() {

	}

	public String findPath(String[] wordPair, Lexicon lexicon) {
		
		// Start and end words
		String startWord = wordPair[FROM].toUpperCase();
		String endWord = wordPair[TO].toUpperCase();
		
		// Path starts with start word
		List<String> startPath = new ArrayList<String>();
		startPath.add(startWord);
		
		// Create two queues: words and path
		Queue<String> words = new LinkedList<String>();
		Queue<List<String>> path = new LinkedList<List<String>>();
		
		// Create array to keep track of already searched words
		List<String> searched = new ArrayList<String>();
		
		words.add(startWord);
		path.add(startPath);
		
//		int i = 0;
		while (!words.peek().equals(endWord) && words != null) {
//			i++;
			System.out.println("Starting Words: " + words);
			System.out.println("Starting Path: " + path);
			
			String currentWord = words.poll();
			List<String> currentPath = path.poll();
			
			System.out.println("Current word: " + currentWord);
			System.out.println("Current path: " + currentPath);
			
			List<String> wordsOneOff = lexicon.wordsOneOff(currentWord);
			System.out.println("Words one off: " + wordsOneOff);
			
			for (String word : wordsOneOff) {		
				if (word.equals(endWord)) {
					currentPath.add(word);
					System.out.println(currentPath);
					return null;
				} else if (!searched.contains(word)){
					words.add(word);
					List<String> tempPath = new ArrayList<String>();
					for (String wordInPath : currentPath) {
						tempPath.add(wordInPath);
					}
					tempPath.add(word);
					path.add(tempPath);
					searched.add(word);
				}
			}
		}
		return null;
	}
}
