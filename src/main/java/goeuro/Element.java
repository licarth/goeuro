package goeuro;

public class Element {
	
	public String _type;
	public long _id;
	public String name;
	public String type;
	
	public GeoPosition geo_position;
	
	public static class GeoPosition {
		public double latitude;
		public double longitude;
	}
}
