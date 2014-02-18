package goeuro;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
	public static void main(String[] args) {
//		Properties props = System.getProperties();
//		props.setProperty("com.sun.net.ssl.checkRevocation", "false");
		
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		try {
			URL url = new URL("https://api.goeuro.com/api/v1/suggest/position/en/name/berlin");
			
			ResultsObject el = mapper.readValue(url, ResultsObject.class);
			
			System.out.println(el.results.get(0).geo_position.latitude);
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
