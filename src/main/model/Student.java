package model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String firstname;
    private String lastname;
    private List<Course> courseList;

    public Boolean addCourse(String subject, Integer number, Integer section) {
        return false; // stub
    }

    public Boolean removeCourse(String subject, Integer number, Integer section) {
        return false; // stub
    }

    public List<Course> getSharedClasses(Student student) {
        return new ArrayList<Course>();
    }

    public List<Course> getCourses() {
        return courseList;
    }
}
