import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


public class Assign2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		File diller1File = new File("diller1.txt");
		File sowpodsFile = new File("sowpods.txt");
		
		
		// Diller
		Diller diller1 = new Diller(diller1File);
		Map<Integer, ArrayList<String[]>> words1 = diller1.getWords();
		System.out.println(diller1);
		
		
		// Lexicon
		Lexicon lexicon1 = new Lexicon();
		lexicon1.open(sowpodsFile);
		lexicon1.parseDictionary(new ArrayList<Integer>(words1.keySet()));
		
		Map<Integer, ArrayList<String>> dictionary1 = lexicon1.getSorted();
		
		System.out.println(lexicon1);

		
		// Dodgson
		Dodgson dodgson = new Dodgson();
		dodgson.findFromDiller(words1, dictionary1);
		
			// Alternatively
			dodgson.findFromDiller(diller1.getWords(), lexicon1.getSorted());
		
		
	}

}
