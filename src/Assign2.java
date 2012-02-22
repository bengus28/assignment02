import java.io.File;


public class Assign2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		File diller1File = new File("diller1.txt");
		File sowpodsFile = new File("sowpods.txt");


		// ParseInput
		ParseInput diller = new ParseInput(diller1File);
		System.out.println(diller);
		
		
		// Lexicon
		Lexicon lexicon = new Lexicon();
		lexicon.open(sowpodsFile);
		lexicon.sortDictionary(diller.getWordLengths());
		System.out.println(lexicon);
			
		// Test some lexicon functionality
		String testWord = "hello";
		System.out.println("isWord(" + testWord + "): " + lexicon.isWord(testWord));
		System.out.println("wordsOneOff(" + testWord + "): " + lexicon.wordsOneOff(testWord));
		
		
		// Dodgson
		Dodgson dodgson = new Dodgson();
		for (String[] wordPair : diller.getWordPairs()) {
			dodgson.findPath(wordPair, lexicon);
		}		
	}

}
