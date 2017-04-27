import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A class containing several static methods related to N-Tuple comparison.
 * This way, code is more modular and reusable.
 * @author gautambhat
 *
 */
public class NTuple {
	
	/**
	 * used to generate the N-Tuple for a string.
	 * 
	 * @param string	The input string.
	 * @param N			value of N
	 * @return			The N-Tuple list for the input string.
	 */
	public static List<String> generateNTuple(String string, int N) {
		
		if (N <= 0 ) {
			throw new IllegalArgumentException("N has to be more than 0!");
		}
		
		if (string.length() <= 0 ) {
			throw new IllegalArgumentException("No valid string provided!");
		}
		
		List<String> nTuples = new ArrayList<String>();
		String[] words = string.replaceAll("\\p{P}", "").toLowerCase().toLowerCase().split(" ");
		
		if (words.length < N) {
			throw new IllegalArgumentException("N cannot be greater than the number of words in the file!");
		}
		
		for (int i = 0; i < words.length - N + 1; i++) {
			
			StringBuilder s = new StringBuilder(words[i]); //Using StringBuilder to optimize concatenation of strings.
			
			for (int j = 1; j < N; j++) {
				s.append(" ");
				s.append(words[j+i]);
			}
			
			nTuples.add(s.toString());
		}
		
		return nTuples;
	}
	
	
	
	/**
	 * Returns the number (or percentage) of matches between two N-Tuple lists.
	 * 
	 * @param NT1			First N-Tuple list
	 * @param NT2			Second N-Tuple list
	 * @param Synonyms		The synonym table to compare two N-Tuples
	 * @param Percentage	Parameter to decide whether to calculate count, or percentage
	 * @return				Count or percentage of matches.
	 */
	public static int countMatches(List<String> NT1, List<String> NT2, HashMap<String, List<String>> Synonyms, boolean Percentage) {
		
		if (Synonyms == null || Synonyms.size() == 0) {
			throw new IllegalArgumentException("Problem with synonym table!");
		}
		
		int count = 0;
		
		for (String ntuple1 : NT1) {
			
			for (String ntuple2 : NT2) {
			
				if (isMatch(ntuple1, ntuple2, Synonyms)) {
					
					++count; //Match! Increment count.
					break;	//Assumed that only one match is required for counting; subsequent matches may alter the correct score
				
				}

			}
		}
		
		/*
		 * If percentage = true, then go ahead and calculate percentage and return
		 */
		if (Percentage == true) {
			
			double percent = 0.0;
			
			if (NT1.size() > 0) {
				percent = (count * 100) / NT1.size();
			}
			
			return (int) ( percent + 0.5d); //rounded off to closest integer
		}
		
		/*
		 * If percentage = false, return count
		 */
		return count;
		
	}
	
	
	/**
	 * Helper method for countMatches(..). Returns true if two n-tuples match.
	 * 
	 * @param NT1		first N-Tuple
	 * @param NT2		second N-Tuple
	 * @param Synonyms	Synonym table
	 * @return			true for match, false for no match
	 */
	public static boolean isMatch(String NT1, String NT2, HashMap<String, List<String>> Synonyms) {
		
		
		String[] nTuple1 = NT1.split(" ");
		String[] nTuple2 = NT2.split(" ");
		
		if (nTuple1.length != nTuple2.length) {
			throw new IllegalArgumentException("Sizes of two tuples do not match!");
		}
		
		if (Synonyms == null || Synonyms.size() == 0) {
			throw new IllegalArgumentException("Error with synonym table!");
		}
		
		for (int i = 0; i < nTuple1.length; i++) {
			
			if (!(nTuple1[i].equals(nTuple2[i]))) {
				
				if (Synonyms.containsKey(nTuple1[i]) && Synonyms.containsKey(nTuple2[i])) {
					
					if(!(Synonyms.get(nTuple1[i]).equals(Synonyms.get(nTuple2[i])))) {
						return false;
					}
					
				} else {
					return false; 
				}
				
			} else {
				continue;
			}
			
		}
		
		return true;
		
	}
}
