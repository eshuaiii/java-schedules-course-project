package model.course;

import model.account.Student;

import java.util.List;

public class Course {
    private String courseName;
    private Integer courseNum;
    private Integer courseSection;
    private List<Student> studentList;

    // EFFECTS: creates a new course given its name, number, and section, then creates an empty studentList.
    public Course(String courseName, Integer courseNum, Integer courseSection) {
        // stub
    }

    // REQUIRES: student is not already in studentList.
    // MODIFIES: this
    // EFFECTS: adds a student to the course's studentList.
    public Boolean addStudent(Student student) {
        return false; // stub
    }

    // REQUIRES: student is in studentList.
    // MODIFIES: this
    // EFFECTS: removes a student from the course's studentList
    public Boolean removeStudent(Student student) {
        return false; // stub
    }

    // MODIFIES: this
    // EFFECTS: searches through studentList for the given student. Returns true if found, false otherwise.
    public Boolean searchStudent(Student student) {
        return false; // stub
    }

    // EFFECTS: returns a concatenated string of the form <courseName><courseNum>-<courseSection>
    public String courseToKey() {
        return this.getCourseName() + this.getCourseNum() + "-" + this.getCourseSection();
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

    public List<Student> getStudentList() {
        return studentList;
    }
}
