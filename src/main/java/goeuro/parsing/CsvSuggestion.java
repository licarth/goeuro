package goeuro.parsing;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Object used by csv mapper to write Csv suggestions.
 * 
 * @author thomas
 *
 */
@JsonPropertyOrder({"_type", "_id", "name", "type", "latitude", "longitude" })
public class CsvSuggestion extends Suggestion{
	
//	Note : These properties are intentionally qualified as public
//	because these classes are particularily simple and mainly used as 
//	descriptions of how to map JSON and CSV entries.
	
	public double latitude;
	public double longitude;

}
