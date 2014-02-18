package goeuro;

import goeuro.parsing.CsvSuggestion;
import goeuro.parsing.JsonRootObject;
import goeuro.parsing.JsonSuggestion;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class JsonParser {

	private static ObjectMapper objectMapper = new ObjectMapper();
	private static CsvMapper csvMapper = new CsvMapper();
	
	public static void main(String[] args) throws InvalidArgumentsException, JsonParseException, JsonMappingException, IOException {

		//TODO Remove line !
		args = new String[]{"paris"};

		if (args.length < 1){
			throw new InvalidArgumentsException("No query string specified. Please specify a query string in you run command line. ex : 'java -jar GoEuroTest.jar \"Berlin\"'");
		}

		//Check string query argument
		String query = args[0];

		//		//Make it URL-safe
		query = URLEncoder.encode(query, "UTF-8");

		ArrayList<JsonSuggestion> jsonSuggestions = getSuggestions(query);

		ArrayList<CsvSuggestion> csvSuggestions = convertToCsvSuggestions(jsonSuggestions);
		
		buildCSV(csvSuggestions);

	}

	private static ArrayList<CsvSuggestion> convertToCsvSuggestions(Collection<JsonSuggestion> jsonSuggestions) {
		ArrayList<CsvSuggestion> csvSuggestions = new ArrayList<CsvSuggestion>();
		for (JsonSuggestion jsonSuggestion : jsonSuggestions) {
			csvSuggestions.add(jsonSuggestion.toCSVSugesstion());
		}
		return csvSuggestions;
	}

	private static void buildCSV(ArrayList<CsvSuggestion> suggestions) throws JsonProcessingException {
		System.out.println(suggestions);
		
		// Schema from POJO (usually has @JsonPropertyOrder annotation)
		CsvSchema schema = csvMapper.schemaFor(CsvSuggestion.class).withUseHeader(true);
		String out = csvMapper.writer(schema).writeValueAsString(suggestions);
		System.out.println(out);
	}

	/**
	 * 
	 * Uses Jackson library with databind package in order to get & parse the JSON file returned by api.
	 * 
	 * @param query
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private static ArrayList<JsonSuggestion> getSuggestions(String query) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<JsonSuggestion> suggestions = null;

		URL url = new URL("https://api.goeuro.com/api/v1/suggest/position/en/name/"+query);
		JsonRootObject el = objectMapper.readValue(url, JsonRootObject.class);

		return el.results;
	}
}
