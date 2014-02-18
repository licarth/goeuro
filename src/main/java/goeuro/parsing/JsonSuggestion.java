package goeuro.parsing;

/**
 * Object used by json mapper to write json suggestions.
 * 
 * @author thomas
 *
 */
public class JsonSuggestion extends Suggestion{
	
//	Note : These properties are intentionally qualified as public
//	because these classes are particularily simple and mainly used as 
//	descriptions of how to map JSON and CSV entries.
	
	public GeoPosition geo_position;
	
	public static class GeoPosition {
		public double latitude;
		public double longitude;
	}
	
	public CsvSuggestion toCSVSugesstion(){
		CsvSuggestion res = new CsvSuggestion();
		
		res._id = this._id;
		res._type = this._type;
		res.name = this.name;
		res.type = this.type;
		res.latitude = this.geo_position.latitude;
		res.longitude = this.geo_position.longitude;
		
		return res;
	}
}
