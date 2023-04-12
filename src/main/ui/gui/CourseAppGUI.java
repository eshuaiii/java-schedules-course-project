package ui.gui;

import model.account.AllAccounts;
import model.account.Student;
import model.course.AllCourses;
import model.course.Course;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// Creates an instance of CourseAppGUI - a reduced version of CourseApp specifically for GUI.
public class CourseAppGUI {
    private static final String JSON_STORE = "./data/session.json";
    protected AllAccounts accountList;
    protected AllCourses courseList;
    protected Student currentStudent;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: creates a new CourseApp and initializes fields + Scanner, then begins at preLoggedInScreen.
    public CourseAppGUI() throws FileNotFoundException {
        currentStudent = null;
        accountList = new AllAccounts();
        courseList = new AllCourses();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        preLoggedInScreen();
    }

    // MODIFIES: this
    // EFFECTS: shows the state before the user is logged in
    private void preLoggedInScreen() {
        new PreLoginFrame(this);
    }

    // MODIFIES: this
    // EFFECTS: loads a previous session from JSON file.
    // modelled based on the JsonSerializationDemo file,
    //  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    protected void loadSession() {
        List<Object> jsonData;
        try {
            jsonData = jsonReader.read();
            accountList = (AllAccounts) jsonData.get(0);
            courseList = (AllCourses) jsonData.get(1);
//            System.out.println("✅ Successfully loaded the data from" + JSON_STORE + " !");
        } catch (IOException e) {
//            System.out.println("⚠️ Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: takes in user inputs for signup workflow.
    //          If no incorrect inputs, creates a new student and adds to accountList.
    protected boolean signup(String username, String firstName, String lastName, String password) {
        if (accountList.getAccountList().get(username) != null) {
//            System.out.println("\n⚠️ Oops - seems like that username is taken.");
            return false;
        } else {
            currentStudent = accountList.signup(username, firstName, lastName, password);
//            System.out.println("\n✅ Signup successful!");
            return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: Takes user inputs and attempts to log user in. If successful, user's Student is assigned and
    //          user is redirected to main menu.
    protected boolean login(String username, String password) {
        currentStudent = accountList.login(username, password);
        if (currentStudent == null) {
//            System.out.println("\n⚠️ Login was not successful.");
            return false;
        } else {
//            System.out.println("\n✅ Login successful!");
            return true;
        }
    }

    // MODIFIES: this, currentStudent, course
    // EFFECTS: adds a course to the currentStudent.
    protected Course addCourse(String courseName, String courseNum, String courseSection) {
        int courseNumInt;
        int courseSectionInt;
        try {
            courseNumInt = Integer.parseInt(courseNum);
            courseSectionInt = Integer.parseInt(courseSection);
            Course course = courseList.addCourse(courseName, courseNumInt, courseSectionInt);
            currentStudent.addCourse(course);
            return course;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // MODIFIES: this, currentStudent, course
    // EFFECTS: removes a course from the currentStudent
    protected void removeCourse(String courseToRemove) {
        Course removedCourse = currentStudent.getCourseList().get(courseToRemove);
        currentStudent.removeCourse(removedCourse);
        this.courseList.checkForEmptyCourse(removedCourse);
//        System.out.println("\n✅ Course removed!");
    }

    // EFFECTS: searches for a course given user inputs
    protected Course searchCourse(String courseName, String courseNum, String courseSection) {
        int courseNumInt = -1;
        int courseSectionInt = -1;
        try {
            courseNumInt = Integer.parseInt(courseNum);
            courseSectionInt = Integer.parseInt(courseSection);
        } catch (NumberFormatException e) {
//            System.out.println("Oops - the course number and section must be an integer!");
        }

        return courseList.searchCourse(courseName, courseNumInt, courseSectionInt);
    }

    // EFFECTS: saves the session to file
    // modelled based on the JsonSerializationDemo file,
    //  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    protected void saveSession() {
        try {
            jsonWriter.open();
            jsonWriter.write(accountList);
            jsonWriter.close();
//            System.out.println("✅ Saved the session to " +  JSON_STORE);
        } catch (FileNotFoundException e) {
//            System.out.println("⚠️ Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: signs the user out of the applet.
    protected void signOut() {
        currentStudent = null;
//        System.out.println("You've been signed out! See you soon. 👋");
    }
}
