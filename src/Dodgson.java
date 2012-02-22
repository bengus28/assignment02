import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Dodgson {

	private final int FROM = 0, TO = 1;

	public Dodgson() {

	}

	public List<String> findPath(String[] wordPair, Lexicon lexicon) {
		
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
		
		while (!words.peek().equals(endWord) && words != null) {

			String currentWord = words.poll();
			List<String> currentPath = path.poll();
						
			List<String> wordsOneOff = lexicon.wordsOneOff(currentWord);
			
			for (String word : wordsOneOff) {		
				if (word.equals(endWord)) {
					currentPath.add(word);
					return currentPath;
				} else if (!searched.contains(word)){
					
					List<String> tempPath = new ArrayList<String>();
					for (String wordInPath : currentPath) {
						tempPath.add(wordInPath);
					}
					tempPath.add(word);
					
					words.add(word);
					path.add(tempPath);
					searched.add(word);
				}
			}
		}
		return null;
	}
}
