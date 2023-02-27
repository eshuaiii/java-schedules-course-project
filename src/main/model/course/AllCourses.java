package model.course;

import java.util.HashMap;

public class AllCourses {
    HashMap<String, Course> courseList;

    // EFFECTS: Initializes AllCourses with an empty HashSet
    public AllCourses() {
        courseList = new HashMap<String, Course>();
    }

    // MODIFIES: this
    // EFFECTS: Creates a new course
    public Course addCourse(String courseName, Integer courseNum, Integer courseSection) {
        Course course = new Course(courseName, courseNum, courseSection);
        if (courseList.get(course.courseToKey()) == null) {
            courseList.put(course.courseToKey(), course);
            return course;
        } else {
            return courseList.get(course.courseToKey());
        }

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

    // EFFECTS: searches and returns the given course, null if not found
    public Course searchCourse(String courseName, Integer courseNum, Integer courseSection) {
        return courseList.get(courseName + "-" + courseNum + "-" + courseSection);
    }

    public HashMap<String, Course> getCourseList() {
        return courseList;
    }
}
