import java.io.File;
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
		
		// Lexicon
		Lexicon wordList = new Lexicon();
		wordList.open(wordListFile);
		wordList.prune(words.getWordLengths());
		
		
		// Test some lexicon functionality
//		String[] testWords = {"hello", "hell", "apple", "ap", "can", "candle"};
//		for (String word : testWords) {
//			String output = "\tWord: " + word;
//				output += "\n\t\tisWord(): " + wordList.isWord(word);
//				output += "\n\t\twordsOneOff(): " + wordList.wordsOneOff(word);
//				output += "\n\t\tisPrefix(): " + wordList.isPrefix(word);
//			System.out.println(output);
//		}
		
		
		// Dodgson
		Dodgson dodgson = new Dodgson();
		
		// For each word pair
		for (String[] wordPair : wordPairs) {
			
			// Print the word pair
			System.out.println("Word pair: " + wordPair[0].toLowerCase() + " È " + wordPair[1].toLowerCase()); 
		
			// Find the path
			List<String> path = dodgson.findPath(wordPair, wordList);
			String output = "\tPath: ";
			if (path != null) {
				int i = 0;
				while (i < path.size() - 1) {
					output += path.get(i).toLowerCase() + " È ";
					i++;
				}
				output += path.get(i).toLowerCase();
				System.out.println(output);
			}
			else
				System.out.println("\tNo path exists.");
			
		}		
	}

}
