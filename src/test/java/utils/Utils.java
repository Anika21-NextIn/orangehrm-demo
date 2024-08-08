package utils;

import config.EmployeeModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static void saveUsers(EmployeeModel model) throws IOException, ParseException {
        String fileLocation = "./src/test/resources/employees.json";
        JSONParser parser = new JSONParser();
       JSONArray empArray = (JSONArray) parser.parse(new FileReader(fileLocation));

       JSONObject empObj = new JSONObject();
       empObj.put("firstName", model.getFirstName());
        empObj.put("lastName", model.getLastName());
        empObj.put("userName", model.getUserName());
        empObj.put("password", model.getPassword());

        empArray.add(empObj);
        FileWriter writer = new FileWriter(fileLocation);
        writer.write(empArray.toJSONString());
        writer.flush();
        writer.close();

    }
     public static JSONObject getUser() throws IOException, ParseException {
        String fileLocation = "./src/test/resources/employees.json";
        JSONParser parser = new JSONParser();
        JSONArray empArray = (JSONArray) parser.parse(new FileReader(fileLocation));
       JSONObject empObj= (JSONObject) empArray.get(empArray.size()-1);
       return empObj;

    }

    public static void main(String[] args) throws IOException, ParseException {
       // saveUsers("Ani", "kilu", "ani21", "kilu123");
    }
}
