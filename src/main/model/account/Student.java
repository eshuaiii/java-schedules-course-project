package model.account;

import model.Event;
import model.EventLog;
import model.course.Course;

import java.util.*;

// Represents a Student profile, with a corresponding username to their Account, first and last name,
// and their course list.
public class Student {
    private String username;
    private String firstName;
    private String lastName;
    private HashMap<String, Course> courseList;

    // EFFECTS: creates a new student with first and last name, and creates an empty courseList.
    public Student(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        courseList = new HashMap<>();
    }

    // MODIFIES: this, course
    // EFFECTS: adds the course to the student's courseList, as well as the course's studentList.
    // TODO: add guard for MR from Course
    public void addCourse(Course course) {
        courseList.put(course.courseToKey(), course);
        course.addStudent(this);
        EventLog.getInstance().logEvent(new Event("✅ Course: " + course.courseLong() + " added to " + username));
    }

    // REQUIRES: course is in courseList
    // MODIFIES: this, course
    // EFFECTS: removes the course from the student's courseList, as well as the course's studentList.
    // TODO: add guard for MR from Course
    public void removeCourse(Course course) {
        courseList.remove(course.courseToKey());
        course.removeStudent(this);
        EventLog.getInstance().logEvent(new Event("✅ Course: " + course.courseLong() + " removed from " + username));
    }

    // EFFECTS: given the student, return a list of courses that this student shares with the given student.
    public List<Course> getSharedCourses(Student s2) {
        HashMap<String, Course> s2Courses = s2.getCourseList();
        List<Course> sharedCourses = new ArrayList<>();

        for (Course c : this.getCourses()) {
            Course hasCourse = s2Courses.get(c.courseToKey());

            if (hasCourse != null) {
                sharedCourses.add(hasCourse);
            }
        }
        return sharedCourses; // TODO: check if empty, if so returns NoCourseSharedException
    }

    // EFFECTS: returns "<firstName> <lastName>"
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // EFFECTS: returns a map of the full course names and their respective keys from courseList.
    public Map<String, String> getCourseNamesGUI() {
        Map<String, String> courseMap = new HashMap<>();

        for (Course c : this.getCourses()) {
            courseMap.put(c.courseLong(), c.courseToKey());
        }


        return courseMap;
    }

    // EFFECTS: given the student, return a list of courses that this student shares with the given student.
    public Map<String, String> getSharedCoursesGUI(Student s2) {
        HashMap<String, Course> s2Courses = s2.getCourseList();
        Map<String, String> sharedCourses = new HashMap<>();

        for (Course c : this.getCourses()) {
            Course hasCourse = s2Courses.get(c.courseToKey());

            if (hasCourse != null) {
                sharedCourses.put(hasCourse.courseLong(), hasCourse.courseToKey());
            }
        }
        if (sharedCourses.size() == 0) {
            return null;
        } else {
            return sharedCourses; // TODO: check if empty, if so returns NoCourseSharedException
        }
    }

    // EFFECTS: returns the courses from courseList, as opposed to the entire HashMap.
    public List<Course> getCourses() {
        return new ArrayList<>(courseList.values());
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public HashMap<String, Course> getCourseList() {
        return courseList;
    }
}
