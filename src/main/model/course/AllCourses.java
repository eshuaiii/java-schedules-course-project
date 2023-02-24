package model.course;

import model.account.Student;

import java.util.ArrayList;
import java.util.List;

public class AllCourses {
    List<Course> courseList;

    // ???
    public AllCourses() {
        // stub
    }

    // creates a new course then adds it to the list
    public Boolean addCourse(String courseName, Integer courseNum, Integer courseSection) {
        return false; // stub
    }

    // checks if a course has an empty studentList: if so, remove the reference.
    public Boolean checkForEmptyCourse(Course course) {
        return false; // stub
    }

    // searches for a course, returns its studentList
    public List<Student> searchCourse(String courseName, Integer courseNum, Integer courseSection) {
        return new ArrayList<>();
    }
}
