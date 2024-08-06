package technosapeince.data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;


public class DataReader {
	
	
	public List<HashMap<String,String>> getJsonDataToMap() throws IOException {
		
		//Reading Json to String
		String jsoncontent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\technosapeince\\data\\CourseData.json"),StandardCharsets.UTF_8);
		
		//Reading String to HashMap (Jackoson Datbind)
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data= mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){
			
		});
		return data;	
		
		
		
	}
	
	

}
