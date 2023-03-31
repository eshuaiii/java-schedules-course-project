package model.course;

import model.account.Student;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents a course with its shorthand name, its course number and section, and a list of the students in the course.
public class Course implements Writable {
    private String courseName;
    private Integer courseNum;
    private Integer courseSection;
    private Collection<Student> studentList;

    // EFFECTS: creates a new course given its name, number, and section, then creates an empty studentList.
    public Course(String courseName, Integer courseNum, Integer courseSection) {
        this.courseName = courseName;
        this.courseNum = courseNum;
        this.courseSection = courseSection;
        studentList = new HashSet<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a student to the course's studentList.
    // TODO: Modify student again here, and set check for MR in Student. Is public a bad idea?
    public Boolean addStudent(Student student) {
        return studentList.add(student);
    }

    // MODIFIES: this
    // EFFECTS: removes a student from the course's studentList
    // TODO: Modify student again here, and set check for MR in Student. Is public a bad idea?
    public Boolean removeStudent(Student student) {
        return studentList.remove(student);
    }

    // EFFECTS: returns a sorted list of studentList in string form
    public List<String> getStudentListSorted() {
        List<String> studentArrayList = new ArrayList<>();

        for (Student s : studentList) {
            studentArrayList.add(s.getFullName());
        }
        Collections.sort(studentArrayList);

        return studentArrayList;
    }

    // EFFECTS: returns a map of the full course names and their respective keys from courseList.
    public Map<String, String> getStudentNamesGUI() {
        Map<String, String> studentMap = new HashMap<>();

        for (Student s : this.getStudentList()) {
            studentMap.put(s.getFullName(), s.getUsername());
        }

        return studentMap;
    }

    // EFFECTS: returns a concatenated string of the form "<courseName>-<courseNum>-<courseSection>"
    public String courseToKey() {
        return this.getCourseName() + "-" + this.getCourseNum() + "-" + this.getCourseSection();
    }

    // EFFECTS: returns a long string of the form "<courseName> <courseNum>, Section <courseSection>"
    public String courseLong() {
        return this.getCourseName() + " " + this.getCourseNum() + ", Section " + this.getCourseSection();
    }

    public String getCourseName() {
        return courseName;
    }

    public Integer getCourseNum() {
        return courseNum;
    }

    public Integer getCourseSection() {
        return courseSection;
    }

    public Collection<Student> getStudentList() {
        return studentList;
    }

    // EFFECTS: returns Course as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("courseName", courseName);
        json.put("courseNum", courseNum);
        json.put("courseSection", courseSection);

        return json;
    }
}
