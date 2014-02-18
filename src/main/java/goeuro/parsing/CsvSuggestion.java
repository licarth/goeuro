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
	
	public double latitude;
	public double longitude;

}
