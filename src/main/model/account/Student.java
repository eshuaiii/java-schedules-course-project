package model.account;

import model.course.Course;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Student {
    private String username;
    private String firstName;
    private String lastName;
    private Collection<Course> courseList;

    // EFFECTS: creates a new student with first and last name, and creates an empty courseList.
    public Student(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        courseList = new HashSet<Course>();
    }

    // REQUIRES: course is not already in courseList
    // MODIFIES: this
    // EFFECTS: adds the course to the student's courseList. Returns true if successful.
    public Boolean addCourse(Course course) {
        return courseList.add(course);
    }

    // REQUIRES: course is in courseList
    // MODIFIES: this
    // EFFECTS: removes the course from the student's courseList. Returns true if successful.
    public Boolean removeCourse(Course course) {
        return courseList.remove(course);
    }

    // TODO
    // EFFECTS: given the student, return a list of courses that this student shares with the given student.
    public List<Course> getSharedClasses(Student student) {
        return new ArrayList<Course>();
    }

    public Collection<Course> getCourses() {
        return courseList;
    }
}
