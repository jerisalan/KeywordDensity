import java.net.URL;
/**
 * 
 * @author Jeris Alan Jawahar
 * @version 1.0
 * @since 2018-04-22
 *
 */
public class Main {
	public static void main(String[] args) {
		// Add check to determine if the number of arguments for the application is correct
		if(args.length != 1){
			System.out.println("Invalid arguments length. Accepts 1 argument which should be a valid URL.");
			return;
		}
		// Check if the URL entered is valid.
		try {
	        URL url = new URL(args[0]);
	        url.toURI();	        
	    } catch (Exception e) {
	        System.out.println("Invalid URL. Please enter a valid URL.");
	        return;
	    }
		// Create a parser object for the given URL
		Parser p = new Parser(args[0]);		
		// Create a word density analyzer object for the given URL parser
		Analyzer a = new Analyzer(p);
		// Build a word density model
		a.buildWordDensity();
		// Display top 10 items along with their corresponding weight/relevance
		a.displayTopItems();		
	}
}
