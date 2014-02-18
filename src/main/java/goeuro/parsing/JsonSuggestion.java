package goeuro.parsing;

public class JsonSuggestion extends Suggestion{
	
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
