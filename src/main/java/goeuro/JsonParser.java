package goeuro;

import goeuro.parsing.CsvSuggestion;
import goeuro.parsing.JsonRootObject;
import goeuro.parsing.JsonSuggestion;

import java.io.File;
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

		if (args.length < 1){
			throw new InvalidArgumentsException("No query string specified. Please specify a query string in you run command line. ex : 'java -jar GoEuroTest.jar \"Berlin\"'");
		}

		//Checks string query argument and makes it URL-safe.
		String query = args[0];
		query = URLEncoder.encode(query, "UTF-8");
		
		//Fetches suggestions querying the API.
		ArrayList<JsonSuggestion> jsonSuggestions = getSuggestions(query);
		
		if (jsonSuggestions.isEmpty()) {
			System.out.println("No suggestions found !");
			return;
		}
		
		//Converts them into csv object mapping format.
		ArrayList<CsvSuggestion> csvSuggestions = convertToCsvSuggestions(jsonSuggestions);
		
		//Builds csv file.
		String fileName = "output-"+query+".csv";
		buildCSV(csvSuggestions, fileName);
		
		//Print a preview
		System.out.println();
		System.out.println("Here are the first results... The entire list is in file : "+fileName);
		System.out.println();
		printCSVPreview(csvSuggestions, 5);

	}

	private static void printCSVPreview(ArrayList<CsvSuggestion> csvSuggestions, int numOfLines) throws JsonProcessingException {
		CsvSchema schema = csvMapper.schemaFor(CsvSuggestion.class).withUseHeader(true);
		int nDisp = Math.min(csvSuggestions.size(), numOfLines);
		System.out.println(csvMapper.writer(schema).writeValueAsString(csvSuggestions.subList(0, nDisp)));
		System.out.println("Displaying "+nDisp+" out of "+csvSuggestions.size()+" results.");
	}

	private static ArrayList<CsvSuggestion> convertToCsvSuggestions(Collection<JsonSuggestion> jsonSuggestions) {
		ArrayList<CsvSuggestion> csvSuggestions = new ArrayList<CsvSuggestion>();
		for (JsonSuggestion jsonSuggestion : jsonSuggestions) {
			csvSuggestions.add(jsonSuggestion.toCSVSugesstion());
		}
		return csvSuggestions;
	}

	private static void buildCSV(ArrayList<CsvSuggestion> suggestions, String fileName) throws IOException {
		CsvSchema schema = csvMapper.schemaFor(CsvSuggestion.class).withUseHeader(true);
		csvMapper.writer(schema).writeValue(new File(fileName), suggestions);
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
