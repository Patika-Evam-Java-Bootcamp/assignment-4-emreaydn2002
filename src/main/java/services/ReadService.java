package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.Main;
import models.StudentDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.System.out;

public class ReadService implements Runnable {
    public volatile static List<StudentDTO> studentList = new ArrayList<StudentDTO>();                                  //  create a studentdto list
    public volatile static Object syncObject = new Object();                                                            //  to create a new object
    public volatile static Boolean isReadProcessDone = false;                                                           //  to check whether the process is done or not

    @Override
    public void run() {
        ObjectMapper mapper = new ObjectMapper();                                                                       //creating new  object
        String json = getResource("students.json");                                                                     //Read student.json file and return as a String
        JSONParser jsonParser = new JSONParser();                                                                       //JSON PARSER class came from Jackson Library. It helps to read json files
        JSONObject parse;

        try {
            synchronized (syncObject) {
                out.println(Thread.currentThread().getName() + " is processing");                                       //  prints the current line
                parse = (JSONObject) jsonParser.parse(json);                                                            //  JSON parse works to parse the given json file
                JSONArray jsonArray = (JSONArray) parse.get("students");                                                //  takes the student block from the given students.json
                studentList = mapper.readValue(jsonArray.toJSONString(), new TypeReference<>() {
                });                                                                                                     //Via object mapper by casting JsonArray to List in the form of Student,it can be used as java object.
                isReadProcessDone = true;                                                                               //   read process information
                Thread.sleep(3000);                                                                               //   thread to wait 3000 ms
                syncObject.notifyAll();                                                                                 //   Notify other sycn block
            }
        } catch (InterruptedException | JsonProcessingException | ParseException ex) {
            ex.printStackTrace();                                                                                       //Handles exceptions
        }
    }

    /**
     * The method returns the read lines from JSON file.
     *
     * @Param resource in the form of String
     * @Return String the read line
     */
    public static String getResource(String resource) {
        StringBuilder json = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream(resource)), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) json.append(line);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException("Reading process failed with a exception given below: " + resource, e);
        }
        return json.toString();
    }
}
