import java.io.File;
import java.util.Arrays;
import java.util.List;


public class Assign2 {

	/**
	 * @param args - No command line arguments.
	 */
	public static void main(String[] args) {
		
		File inputFile = new File("diller1.txt");
		// TODO Make sure wordListFile is "wordLists/sowpods.txt" before submitting assignment
		File wordListFile = new File("wordlists/sowpods.txt");


		// ParseInput
		ParseInput words = new ParseInput(inputFile);
		List<String[]> wordPairs = words.getWordPairs();
		System.out.println(words);
		
		
		// Lexicon
		Lexicon wordList = new Lexicon();
		wordList.open(wordListFile);
		wordList.prune(words.getWordLengths());
		System.out.println(wordList);
		
		
		// Test some lexicon functionality
//		String testWord = "hello";
//		System.out.println("isWord(" + testWord + "): " + lexicon.isWord(testWord));
//		System.out.println("wordsOneOff(" + testWord + "): " + lexicon.wordsOneOff(testWord));
//		System.out.println("isPrefix(" + testWord + "): " + lexicon.isPrefix(testWord));
		
		
		// Dodgson
		Dodgson dodgson = new Dodgson();
		
		// For each word pair
		for (String[] wordPair : wordPairs) {
			
		// Print the word pair
		System.out.println("Words: " + Arrays.toString(wordPair));
			
			// Find the path
			List<String> path = dodgson.findPath(wordPair, wordList);
			if (path != null)
				System.out.println("\tPath: " + path);
			else
				System.out.println("\tNo path exists.");
			
		}		
	}

}
