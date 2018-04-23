import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Jeris Alan Jawahar
 * @version 1.0
 * @since 2018-04-22
 *
 */
public class StopWords {
	// Create a set of all the provided stop word items
	public static Set<String> stopWordSet = new HashSet<String>(Arrays.asList(Constants.stopwords));
	
	/**
	   * This function to check if the given text is a stop word is not
	   * @param word String to be evaluated as stop word or not
	   * @return boolean Returns true if word is a stop word else false
	   */
	public static boolean isStopword(String word) {
		if(word.length() <= 2){
			return true;
		} else if(stopWordSet.contains(word)){ 
			return true;
		} else { 
			return false;
		}
	}
	
	// This function returns a new string representation with all the stop words removed.
	/**
	   * This method returns a new string representation with 
	   * all the stop words removed.
	   * @param str This is the first parameter to addNum method
	   * @return string String representation after removing stopwords
	   */
	public static String removeStopWords(String str) {
		String result = "";
		String[] words = str.toLowerCase().split("\\s+"); // Split with whitespace characters as delimiter
		for(String word : words) {
			if(word.isEmpty()) 
				continue;
			if(isStopword(word)) 
				continue;
			result += (word+" ");
		}
		return result;
	}
}
