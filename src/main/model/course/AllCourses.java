package model.course;

import model.account.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllCourses {
    HashMap<String, Course> courseList;

    // EFFECTS: Initializes AllCourses with an empty HashSet
    public AllCourses() {
        courseList = new HashMap<String, Course>();
    }

    // MODIFIES: this
    // EFFECTS: Creates a new course
    public Boolean addCourse(String courseName, Integer courseNum, Integer courseSection) {
        Course course = new Course(courseName, courseNum, courseSection);
        courseList.put(course.courseToKey(), course);
        return true;
    }

    // MODIFIES: this
    // EFFECTS: checks if a course has an empty studentList: if so, remove the reference.
    public Boolean checkForEmptyCourse(Course course) {
        if (course.getStudentList().size() == 0) {
            courseList.remove(course.courseToKey());
            return true;
        } else {
            return false;
        }
    }

    // TODO: use key method to search for item. If not null, return list, otherwise return null.
    // searches and returns the given course, null if not found
    public Course searchCourse(String courseName, Integer courseNum, Integer courseSection) {
        return courseList.get(courseName + courseNum + "-" + courseSection);
    }
}
