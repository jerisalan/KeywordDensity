import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;
/**
 * 
 * @author Jeris Alan Jawahar
 * @version 1.0
 * @since 2018-04-22
 *
 */
public class Analyzer {
	
	public HashMap<String, Integer> topics;
	public int ngram_size;
	public Parser p;
	
	public Analyzer(Parser p){
		this.topics = new HashMap<String, Integer>();
		this.ngram_size = 2;
		this.p = p;
	}
	
	public Analyzer(HashMap<String, Integer> topics, int size, Parser p){
		this.topics = topics;
		this.ngram_size = size;
		this.p = p;
	}
	
	/**
	   * This method is used to sort a hashmap in descending order 
	   * according it its value. 
	   * @param map HashMap to be sorted by its values
	   * @return Map Hashmap sorted by its value in descending order
	   */
	public <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
            new Comparator<Map.Entry<K,V>>() {
                @Override 
                public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                    int res = e2.getValue().compareTo(e1.getValue());
                    return res != 0 ? res : 1; // Special fix to preserve items with equal values
                }
            }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
	
	/**
	   * This method is used to generate n-gram text for a 
	   * given sentence.
	   * @param str String whose n-grams are to be generated
	   * @param n  Number of sequence of samples to be generated
	   * @param value Weight for each n-gram item generated 
	   * @return Nothing
	   */
	public void ngrams(String str, int n, int value) {
        String[] words = str.split("\\s+");
        for (int i = 0; i < words.length - n + 1; i++){
        	String word = concat(words, i, i+n);
        	if(this.topics.containsKey(word))
        		this.topics.put(word, this.topics.get(word) + (n*value));
        	else
        		this.topics.put(word, (n*value));
        }      
    }
	
	/**
	   * This method is used as a helper function to generate
	   * the n-gram word given its start and end position
	   * @param words Array of words used to generate n-grams
	   * @param start  Start position of n-gram word
	   * @param end End position for n-gram word 
	   * @return String The generated n-gram word representation
	   */
    public String concat(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++)
            sb.append((i > start ? " " : "") + words[i]);
        return sb.toString();
    }
	
    /**
	   * This method is used to perform the word density analysis
	   * by generating the n-grams and calculating the respective weights. 
	   * @return Nothing
	   */
	public void buildWordDensity(){
		this.p.getHTMLDocument();
		for (int n = 1; n <= 3; n++) {
			ngrams(this.p.getMetaDescription(), n, Constants.DESC_WEIGHT);
			ngrams(this.p.getDocTitle(), n, Constants.TITLE_WEIGHT);
			ngrams(this.p.getBoldElements(), n, Constants.STRONG_WEIGHT);
			ngrams(this.p.getMetaKeywords(), n, Constants.KEYWORD_WEIGHT);
			ngrams(this.p.getContent(), n, Constants.NORMAL_WEIGHT);
		}
	}
	
	/**
	   * This method is used to print all the topics generated
	   * on performing word density analysis of the HTML page 
	   * @return Nothing
	   */
	public void displayTopics(){
		for(Map.Entry<String, Integer> entry : this.topics.entrySet()){
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
	
	/**
	   * This method is used to display the top 10 topics
	   * of the word density analysis. 
	   * @return Nothing
	   */
	public void displayTopItems(){
		System.out.println("Displaying top 10 topics(Format - topic item : weight/relevance):");
		int num = 0;
		for (Entry<String, Integer> entry  : entriesSortedByValues(this.topics)) {
		    if(++num <= 10)
		    	System.out.println(entry.getKey()+":"+entry.getValue());
		    else
		    	break;
		}
	}		
}
