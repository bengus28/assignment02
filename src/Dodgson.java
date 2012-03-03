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
		
		// Null if the word pair is valid. Otherwise contains the invalid word(s)
		List<String> isValidWordPair = lexicon.isValidWordPair(wordPair);
		
		// Check to see if word pair is valid
		if (isValidWordPair != null) {
			// The word pair isn't valid
			
			// Print the invalid words
			for (String word : isValidWordPair) {
				System.out.println("\t" + word + " is not a valid word.");
			}
			// Don't continue
			return null;
		}
		
		// Get start and end words from the wordPair array
		String startWord = wordPair[ParseInput.START_WORD_INDEX].toUpperCase();
		String endWord = wordPair[ParseInput.END_WORD_INDEX].toUpperCase();
		
		// Create two queues: words and path
		Queue<String> words = new LinkedList<String>();
		Queue<List<String>> path = new LinkedList<List<String>>();
		
		// Create an array to keep track of already searched words
		List<String> searched = new ArrayList<String>();
		
		// Create the origin path containing the start word
		List<String> originPath = new ArrayList<String>();
		originPath.add(startWord);
		
		words.add(startWord);	// Add startWord to the words queue
		path.add(originPath);	// Add originPath to the path queue
		
		// Loop through the queue until there are no more words
		while (!words.isEmpty()) {
			
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
						// If it is, add it to the path and return the path.
						currentPath.add(word);
						return currentPath;
					/* 
					 * Otherwise, if the word hasn't been searched before,
					 * add it and its path to their respective queues
					 */
					} else if (!searched.contains(word)){
						/* 
						 * Create a throw-away path List. Do this so that the path for
						 * each word doesn't contain the previous wordOneOff word
						 */
						List<String> tempPath = new ArrayList<String>();
						for (String wordInPath : currentPath) {
							tempPath.add(wordInPath);
						}
						tempPath.add(word); // And add the word to the path
						
						/* 
						 * Add the word to the words queue and the searched List.
						 * Add its path to the path queue.
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
