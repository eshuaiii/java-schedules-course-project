package model.course;

import model.Event;
import model.EventLog;

import java.util.HashMap;
import java.util.Map;

// Represents an object containing all courses in the applet session.
public class AllCourses {
    private Map<String, Course> courseList;

    // EFFECTS: Initializes AllCourses with an empty HashMap
    public AllCourses() {
        courseList = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: Creates a new course
    public Course addCourse(String courseName, Integer courseNum, Integer courseSection) {
        Course course = new Course(courseName, courseNum, courseSection);
        if (courseList.get(course.courseToKey()) == null) {
            courseList.put(course.courseToKey(), course);
            EventLog.getInstance().logEvent(new Event("✅ Course: " + course.courseLong() + " added to courseList."));
            return course;
        } else {
            EventLog.getInstance().logEvent(new Event("ℹ️ Course: " + course.courseLong() + " exists in courseList."));
            return courseList.get(course.courseToKey());
        }

    }

    // MODIFIES: this
    // EFFECTS: checks if a course has an empty studentList: if so, remove the reference to the course.
    public Boolean checkForEmptyCourse(Course course) {
        if (course.getStudentList().size() == 0) {
            EventLog.getInstance().logEvent(new Event(
                    "ℹ️ Empty course: " + course.courseLong() + " removed from courseList."));
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

    public Map<String, Course> getCourseList() {
        return courseList;
    }
}
