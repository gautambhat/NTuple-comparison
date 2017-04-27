import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to create a plagiarism checker object in the driver class.
 * Checks plagiarism between two strings using N-Tuple comparison, and a synonym text file.
 * Therefore, files or website data can both be checked for plagiarism by passing in String format.
 * The percentage variable is set to false, if we want to only count the number of matches.
 */
public class PlagiarismChecker {
	
	private HashMap<String, List<String>> synonyms;
	private List<String> nTuples1;
	private List<String> nTuples2;
	private boolean percentage;
	
	/**
	 * 
	 * @param File1Contents	Contents of first file
	 * @param File2Contents	Contents of second file
	 * @param SynonymFile	Path of file containing synonyms
	 * @param N				Size of tuples (N)
	 * @param Percentage	Set to false for count, true for percentage
	 */
	public PlagiarismChecker(String File1Contents, String File2Contents, String SynonymFile, int N, boolean Percentage) {
		nTuples1 = NTuple.generateNTuple(File1Contents, N);
		nTuples2 = NTuple.generateNTuple(File2Contents, N);
		synonyms = new HashMap<String, List<String>>();
		generateSynonymTable(SynonymFile);
		this.percentage = Percentage;
	}
	
	/**
	 * Returns string representation of PlagiarismChecker object
	 */
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("\nFile1: " + nTuples1.toString());
		s.append("\nFile2: " + nTuples2.toString());
		s.append("\nSynonyms: " + synonyms.toString());
		s.append("\nReturn Percentage: " + percentage);
		
		return s.toString();
	}
	
	/**
	 * Usually invoked from constructor to construct the synonym data structure.
	 * 
	 * @param synonymFile path of file containing synonyms
	 */
	private void generateSynonymTable(String synonymFile) {
	
		try {
		    BufferedReader reader = new BufferedReader(new FileReader(synonymFile));
		    String line;
		    while ((line = reader.readLine()) != null)
		    {
		    	List<String> oneLine = new ArrayList<String>();
		    	for (String word : line.split(" ")) {
		    		oneLine.add(word);
		    	}
		    	for (String word : oneLine) {
		    		synonyms.put(word, oneLine); //Assumed that each word is part of only one set of synonyms
		    	}
		    }
		    
		}
		catch (Exception ex)
		{
			System.err.println("Exception occurred trying to read " + synonymFile + ".");
			ex.printStackTrace();
		}
	
	}
	
	/**
	 * Method to check for plagiarism between nTuples1 and nTuples2.
	 * Calls static method of NTuple to calculate plagiarism. 
	 * @return the count or percentage of matches between two files
	 */
	public int checkPlagiarism() {
		return NTuple.countMatches(nTuples1, nTuples2, synonyms, percentage);
	}
	
	/**
	 * Method to override the default behaviour of plagiarism checker.
	 * Calls static method of NTuple to calculate plagiarism along with the Percentage argument, instead of the object's percentage value.
	 * @return the count or percentage of matches between two files
	 */
	public int checkPlagiarism(boolean Percentage) {
		return NTuple.countMatches(nTuples1, nTuples2, synonyms, Percentage);
	}

}
