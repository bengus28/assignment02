import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Finds the Dodgson sequence between two words.
 * 
 * @author Adam Bulgatz and Ben Gustafson
 *
 */
public class Dodgson {

	/**
	 * Location of start and end words in the String[] array
	 */
	private static final int START_WORD_INDEX = 0, END_WORD_INDEX = 1;

	public Dodgson() {

	}

	/**
	 * Find the Dodgson sequence between two words.
	 * 
	 * @param wordPair - String[] array containing a word pair.
	 * @param lexicon - A lexicon object, preferably sorted.
	 * @return A List of Strings representing the Dodgson sequence path.
	 */
	public List<String> findPath(String[] wordPair, Lexicon lexicon) {
		
		// Start and end words
		String startWord = wordPair[START_WORD_INDEX].toUpperCase();
		String endWord = wordPair[END_WORD_INDEX].toUpperCase();
		
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
		
		while (!words.isEmpty() && !words.peek().equals(endWord)) {
						
			String currentWord = words.poll();
			List<String> currentPath = path.poll();
						
			List<String> wordsOneOff = lexicon.wordsOneOff(currentWord);
			
			if (wordsOneOff != null) {
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
		}
		return null;
	}
}
