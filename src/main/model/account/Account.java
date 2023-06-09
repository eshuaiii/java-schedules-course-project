package model.account;

import model.Event;
import model.EventLog;
import model.course.Course;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents an account for a user, with their basic details, username, password, and a corresponding Student object.
public class Account implements Writable {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Student student;

    // EFFECTS: creates a new account with username, first and last name, and password,
    //          then creates a new Student inside the Account.
    public Account(String username, String firstName, String lastName, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.student = new Student(username, firstName, lastName);
        // stub
    }

    // EFFECTS: checks if the password matches the account.
    public Student login(String pw) {
        if (this.password.equals(pw)) {
            EventLog.getInstance().logEvent(new Event("✅ " + this.username + " has logged in."));
            return this.student; // stub
        } else {
            EventLog.getInstance().logEvent(new Event("⚠️ Incorrect password for: " + this.username));
            return null; // TODO: throw IncorrectPasswordException
        }
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // TODO: I don't want a publicly available getPassword method, but I need it for tests. How do fix?
    public String getPassword() {
        return password;
    }

    public Student getStudent() {
        return student;
    }

    // EFFECTS: returns Account as a JSON object.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("firstName", firstName);
        json.put("lastName", lastName);
        json.put("password", password);

        JSONArray courseListJson = new JSONArray();
        for (Course c : student.getCourseList().values()) {
            courseListJson.put(c.toJson());
        }

        json.put("courseList", courseListJson);

        return json;
    }

}
