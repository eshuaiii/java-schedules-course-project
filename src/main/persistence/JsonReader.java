package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.account.AllAccounts;
import model.account.Student;
import model.course.AllCourses;
import model.course.Course;
import org.json.*;

// Represents a reader that reads AllAccounts and AllCourses from JSON data stored in file
// modelled based on the JsonSerializationDemo file, https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;
    private AllAccounts accountList;
    private AllCourses courseList;

    // EFFECTS: constructs a JSON reader to read from source file, as well as initializing the main fields
    public JsonReader(String source) {
        this.source = source;
        accountList = new AllAccounts();
        courseList = new AllCourses();
    }

    // MODIFIES: this
    // EFFECTS: reads the accounts from file and parses it into AllAccounts and AllCourses.
    // Then, assigns each to their respective objects, and returns a list of these objects.
    // throws IOException if an error occurs reading data from file
    public List<Object> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        parseAccountList(jsonObject);
        ArrayList<Object> result = new ArrayList<>();
        result.add(accountList);
        result.add(courseList);
        return result;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses the Accounts list from JSON object, and begins operating on each.
    private void parseAccountList(JSONObject jsonObject) {
        JSONArray accountListArray = jsonObject.getJSONArray("accountList");
        for (Object json : accountListArray) {
            JSONObject nextAccount = (JSONObject) json;
            addAccount(nextAccount);
        }
    }

    // MODIFIES: this
    // EFFECTS: parses each Account from JSON object and adds it to accountList.
    // Also begins operating on the courses in the Student object.
    private void addAccount(JSONObject jsonObject) {
        String firstName = jsonObject.getString("firstName");
        String lastName = jsonObject.getString("lastName");
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        JSONArray courseListArray = jsonObject.getJSONArray("courseList");

        Student s = accountList.signup(username, firstName, lastName, password);
        for (Object json : courseListArray) {
            JSONObject nextCourse = (JSONObject) json;
            Course c = addCourse(nextCourse);
            s.addCourse(c);
        }
    }

    // MODIFIES: this
    // EFFECTS: parses each Course from JSON object and adds it to courseList.
    private Course addCourse(JSONObject jsonObject) {
        String courseName = jsonObject.getString("courseName");
        Integer courseSection = jsonObject.getInt("courseSection");
        Integer courseNum = jsonObject.getInt("courseNum");

        return courseList.addCourse(courseName, courseNum, courseSection);
    }
}
