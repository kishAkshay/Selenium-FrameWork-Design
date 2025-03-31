package ReadDataFromJsonFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;




public class DataReader {
	
	public List<HashMap<String, String>> getJsonToMap(String filepath) throws IOException {
		//below line is to read the json data and covert them in to string
		String jsonContent = FileUtils.readFileToString(new File(filepath),
				StandardCharsets.UTF_8);
		
		//below line is covert the string to hashmap by usding jackson databind dependency 
		//<List<HashMap<String,String>>> all the hashmap will put into one list and store into one variable 
		// and return the list of hashmap
		 
		ObjectMapper mapper = new ObjectMapper();
		 List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
}
