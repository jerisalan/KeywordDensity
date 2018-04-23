import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 
 * @author Jeris Alan Jawahar
 * @version 1.0
 * @since 2018-04-22
 *
 */
public class Parser {
	public String url;
	public String content;
	public Document document;
	
	public static String[] stopwords = {"a", "as", "able", "about", "above", "according", "accordingly", "across", "actually", "after", "afterwards", "again", "against", "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking", "associated", "at", "available", "away", "awfully", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both", "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain", "certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "course", "currently", "definitely", "described", "despite", "did", "didnt", "different", "do", "does", "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero"};	
	
	public Parser(){
		this.url = Constants.default_url2;
		this.content = "";
		this.document = null;
	}
	
	public Parser(String url){
		this.url = url;
		this.content = "";
		this.document = null;
	}
	
	public Parser(String url, String content, Document doc){
		this.url = url;
		this.content = content;
		this.document = doc;	
	}
	
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
	/**
	   * This method is used to return the title of a HTML document
	   * @return String Returns title of HTML document
	   */
	public String getDocTitle(){
		return processString(this.document.title());
	}
	
	/**
	   * This method is used to retrieve the description of the HTML page
	   * @return String HTML page description
	   */
	public String getMetaDescription(){
		if(this.document.select("meta[name=description]").isEmpty())
			return "";
		return processString(this.document.select("meta[name=description]").get(0).attr("content"));
	}
	
	/**
	   * This method is used to retrieve the description of the HTML 
	   * page used in SEO.
	   * @return String HTML page keywords
	   */
	public String getMetaKeywords(){
		if(this.document.select("meta[name=keywords]").isEmpty())
			return "";
		return processString(this.document.select("meta[name=keywords]").get(0).attr("content"));
	}
	
	/**
	   * This method is used to get the text content in a HTML page 
	   * @return Nothing
	   */
	public void getHTMLDocument(){
		try{
			this.document = Jsoup.connect(this.url).get();
		}catch(IOException e){
			e.printStackTrace();
		}
		this.content = processString(this.document.body().text());		
	}
	
	/**
	   * This method is used to fetch all the content appearing 
	   * within <strong> HTML tag.
	   * @return String Content with <strong> style in a HTML page
	   */
	public String getBoldElements(){
		Elements strongTags = this.document.getElementsByTag("strong");
		StringBuilder strongContent = new StringBuilder();
		for (Element tag : strongTags) {
		    strongContent.append(tag.text() + " ");
		}
		return processString(strongContent.toString());
	}
	
	/**
	   * This method is used to process String content before doing
	   * word density analysis.
	   * @param str String to be process
	   * @return String Processed string on which WDA is to be done
	   */
	public String processString(String str){
		if(str == null || str.length() == 0){
			return "";
		}
		str = str.replaceAll("\'",""); // Remove all single quotes
		str = str.replaceAll("\"", ""); // remove all double quotes		
		// Remove some special characters
		str = str.replace("“", "");
	    str = str.replace("”", "");
	    str = str.replace("&", "");	    
	    str = str.replace("?", "");
	    str = str.replace("©", "");
	    str = str.replace(":", "");
	    str = str.replace("!", "");
	    str = str.replace(",", "");
	    str = str.replace(":", "");	       
	    str = str.replace("(", "");
	    str = str.replace(")", "");
	    str = str.replace("{", "");
	    str = str.replace("}", "");
	    str = str.replace("[", "");
	    str = str.replace("]", "");
	    str = str.replace(">", "");
	    str = str.replace("<", "");
	    str = str.replace("+", "");
	    str = str.replace("/", "");
	    str = str.replace("*", "");	    
	    str = str.replace("_", "");
	    str = str.replace(".", "");
	    str = str.trim();
	    // Remove stop-words
		return StopWords.removeStopWords(str);
	}	
}
