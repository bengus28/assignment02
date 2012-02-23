import java.io.File;
import java.util.Arrays;
import java.util.List;


public class Assign2 {

	/**
	 * @param args - No command line arguments.
	 */
	public static void main(String[] args) {
		
		File diller1File = new File("diller1.txt");
		File sowpodsFile = new File("wordlists/2of12.txt"); // Using a more common word list just for fun


		// ParseInput
		ParseInput diller = new ParseInput(diller1File);
//		System.out.println(diller);
		
		
		// Lexicon
		Lexicon lexicon = new Lexicon();
		lexicon.open(sowpodsFile);
		lexicon.sortDictionary(diller.getWordLengths());
//		System.out.println(lexicon);
			
		// Test some lexicon functionality
//		String testWord = "hello";
//		System.out.println("isWord(" + testWord + "): " + lexicon.isWord(testWord));
//		System.out.println("wordsOneOff(" + testWord + "): " + lexicon.wordsOneOff(testWord));
//		System.out.println("isPrefix(" + testWord + "): " + lexicon.isPrefix(testWord));
		
		
		// Dodgson
		Dodgson dodgson = new Dodgson();
		for (String[] wordPair : diller.getWordPairs()) {
			System.out.println("Words: " + Arrays.toString(wordPair));
			List<String> path = dodgson.findPath(wordPair, lexicon);
			if (path != null)
				System.out.println("\tPath: " + path);
			else
				System.out.println("\tNo path exists.");
		}		
	}

}
