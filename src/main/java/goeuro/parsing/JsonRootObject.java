package goeuro.parsing;

import java.util.ArrayList;

/**
 * Root of the json as described in the document.
 * 
 * @author thomas
 *
 */
public class JsonRootObject {

//	Note : These properties are intentionally qualified as public
//	because these classes are particularily simple and mainly used as 
//	descriptions of how to map JSON and CSV entries.
	
	public ArrayList<JsonSuggestion> results;
	
}
