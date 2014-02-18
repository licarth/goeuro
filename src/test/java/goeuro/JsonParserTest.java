package goeuro;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParserTest {

	@Test
	public void test() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			ResultsObject o = mapper.readValue(new File("resources/element1.json"), ResultsObject.class);

			assertEquals(2, o.results.size());
			
			//Checks element 0
			Element el = o.results.get(0);
			assertEquals("Position", el._type);
			assertEquals(410978, el._id);
			assertEquals("Potsdam, USA", el.name);
			assertEquals(44.66978, el.geo_position.latitude, 0.00001);
			assertEquals(-74.98131, el.geo_position.longitude, 0.00001);
			
			//Checks element 1
			el = o.results.get(1);
			assertEquals("Position", el._type);
			assertEquals(377078, el._id);
			assertEquals("Potsdam, Deutschland", el.name);
			assertEquals(52.39886, el.geo_position.latitude, 0.00001);
			assertEquals(13.06566, el.geo_position.longitude, 0.00001);
			
			
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
