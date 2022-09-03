package jsonproject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDataFromJasonFile {

	public static void main(String[] args) throws IOException, ParseException {
		
		JSONParser jsonparser= new JSONParser();
		
		FileReader filereader=new FileReader(".\\Jsonfiles\\employee.json");
		
		Object obj=jsonparser.parse(filereader);	//this is normal Java Object
		
		//type casting
		JSONObject empjsonobj=(JSONObject)obj;
		
		String fname=(String)empjsonobj.get("firstname");
		String lname=(String)empjsonobj.get("lastname");
		
		System.out.println(fname+" "+lname);
		
		JSONArray array=(JSONArray)empjsonobj.get("address");
		
		for(int i=0;i<array.size();i++) {
			
			JSONObject address=(JSONObject)array.get(i);
			
			String street=(String)address.get("street");
			String city=(String)address.get("city");
			String state=(String)address.get("state");
			
//			System.out.println(street+" "+city+" "+state);
			
			System.out.println("Address of "+i+"is...");
			System.out.println("Street :"+street);
			System.out.println("City :"+city);
			System.out.println("State :"+state);
		}
	}

}
