import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class Lexicon implements ILexicon {

	private Scanner inFile;
	@Override
	public void open(File filename) {
		// TODO Auto-generated method stub
		try {
			inFile = new Scanner(filename);
		} catch (FileNotFoundException e) {
			
		}
	}
	
	public void sort() {
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isWord(String str) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPrefix(String str) {
		// TODO Auto-generated method stub
		return false;
	}
	 
	public String[] oneOff(String searchTerm)
	{
		String[] results = null;
		return results;
	}

	@Override
	public Iterator iterator() {
		// hey man
		return null;
	}

}
