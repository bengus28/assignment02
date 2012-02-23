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
		
		words.add(startWord);	// Add startWord to words queue
		path.add(startPath);	// Add startPath to path queue
		
		// Loop through the queue until there are no more words
		while (!words.isEmpty() && !words.peek().equals(endWord)) {
			
			// Poll both of the queues for the a word and its path
			String currentWord = words.poll();
			List<String> currentPath = path.poll();
			
			// Get a list of wordsOneOff from the currentWord
			List<String> wordsOneOff = lexicon.wordsOneOff(currentWord);
			
			// Check to make sure wordsOneOff isn't empty
			if (!wordsOneOff.isEmpty()) {
				// For each word in wordsOneOff
				for (String word : wordsOneOff) {
					// Check to see if that word is the end word
					if (word.equals(endWord)) {
						currentPath.add(word);
						// Add it to the path and return the path if it is.
						return currentPath;
					/* 
					 * Otherwise, if the word hasn't been searched before,
					 * add each word and its path to their respective queues
					 */
					} else if (!searched.contains(word)){
						/* 
						 * Create a throw-away path List. Do this so that the path for
						 * each wordOneOff doesn't contain the previous wordOneOff
						 */
						List<String> tempPath = new ArrayList<String>();
						for (String wordInPath : currentPath) {
							tempPath.add(wordInPath);
						}
						tempPath.add(word); // And add the word to the path
						
						/* 
						 * Add the word to the 'word' queue and 'searched' List,
						 * and its path to the 'path' queue
						 */
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
