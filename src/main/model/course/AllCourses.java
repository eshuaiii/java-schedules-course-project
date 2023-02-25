package model.course;

import model.account.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class AllCourses {
    // TODO: read more about hashmap to understand better!
    HashMap<String, Course> courseList;

    // EFFECTS: Initializes AllCourses with an empty HashSet
    public AllCourses() {
        courseList = new HashMap<String, Course>();
    }

    // TODO: add the proper syntax
    // MODIFIES: this
    // EFFECTS: Creates a new course
    public Boolean addCourse(String courseName, Integer courseNum, Integer courseSection) {
        Course course = new Course(courseName, courseNum, courseSection);
        return courseList.put(course);
    }

    // TODO: figure out what returns from remove
    // checks if a course has an empty studentList: if so, remove the reference.
    public Boolean checkForEmptyCourse(Course course) {
        if (course.getStudentList().size() == 0) {
            return courseList.remove(course.courseToKey());
        } else {
            return false;
        }
    }

    // searches for a course, returns its studentList
    public List<Student> searchCourse(String courseName, Integer courseNum, Integer courseSection) {
        return new ArrayList<>();
    }
}
