import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

public class Dodgson {

	private final int FROM = 0, TO = 1;
	
	public Dodgson() {
		
	}
	
	public ArrayList<String> findPath(String[] wordPair, Lexicon lexicon) {
		String startWord = wordPair[FROM];
		String endWord = wordPair[TO];
		
		int wordLength = startWord.length();
		
		List<String> dictionary = lexicon.getSorted().get(wordLength);
		
		// Print
			String printOutput = "";
			printOutput += "Dodgson Sequence";
			printOutput += "\n\tStart word: " + startWord;
			printOutput += "\n\tEnd word: " + endWord;
			// Print first n elements of each dictionary subgroup
			int n = 10;
			printOutput += "\n\tFirst " + n + " words from dictionary of same length as word pair:";
			printOutput += "\n\t\t[";
			for (int i = 0; i < n; i++) {
				printOutput += dictionary.get(i);
				printOutput += i < (n - 1) ? ", " : "";
			}
			printOutput += (n < dictionary.size()) ? ", ..." : "";
			printOutput += "]";
			
			System.out.println(printOutput);
		// End Print
		
		return null;
	}
	public String findUsingQueue (String[] wordPair, Lexicon lexicon)
	{
		String startWord = wordPair[FROM];
		String endWord = wordPair[TO];
		
		Queue<String> words = new LinkedList<String>();
		Queue<String> path = new LinkedList<String>();
		ArrayList<String> searched = new ArrayList<String>();
		words.add(startWord);
		path.add("");
		
		while ( (words.peek() != endWord ) && (words != null))
		{
			String currentWord = words.poll();
			String currentPath = path.poll();
			List<String> oneOffCurrent = lexicon.wordsOneOff(currentWord);
			
			while (oneOffCurrent != null)
			{
				String current = oneOffCurrent.get(0);
				
				if (!searched.contains(current))
				{
					words.add(current);
					searched.add(current);
					path.add(currentPath + "," + currentWord);
				}
			}
		}
		if (words != null)
			return path.poll() + "," + words.poll();
		else
			return null;
	}
}
