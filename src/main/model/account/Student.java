package model.account;

import model.course.Course;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private List<Course> courseList;

    // EFFECTS: creates a new student with first and last name, and creates an empty courseList.
    public Student(String firstName, String lastName) {
        // stub
    }

    // REQUIRES: course is not already in courseList
    // MODIFIES: this
    // EFFECTS: adds the course to the student's courseList
    public Boolean addCourse(Course course) {
        return false; // stub
    }

    // REQUIRES: course is in courseList
    // MODIFIES: this
    // EFFECTS: removes the course from the student's courseList
    public Boolean removeCourse(Course course) {
        return false; // stub
    }

    // EFFECTS: given the student, return a list of courses that this student shares with the given student.
    public List<Course> getSharedClasses(Student student) {
        return new ArrayList<Course>();
    }

    public List<Course> getCourses() {
        return courseList;
    }
}
