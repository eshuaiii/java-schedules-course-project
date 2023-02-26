package model.account;

import model.course.Course;

import java.util.*;

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
        courseList = new HashMap<String, Course>();
    }

    // REQUIRES: course is not already in courseList
    // MODIFIES: this
    // EFFECTS: adds the course to the student's courseList, as well as the course's studentList.
    //          Returns true if successful.
    public Boolean addCourse(Course course) {
        courseList.put(course.courseToKey(), course);
        course.addStudent(this);
        return true;
    }

    // REQUIRES: course is in courseList
    // MODIFIES: this
    // EFFECTS: removes the course from the student's courseList, as well as the course's studentList.
    //          Returns true if successful.
    public Boolean removeCourse(Course course) {
        courseList.remove(course.courseToKey());
        course.removeStudent(this);
        return true;
    }

    // EFFECTS: given the student, return a list of courses that this student shares with the given student.
    public List<Course> getSharedCourses(Student s2) {
        HashMap<String, Course> s2Courses = s2.getCourseList();
        List<Course> sharedCourses = new ArrayList<Course>();

        for (Course c : this.getCourses()) {
            Course hasCourse = s2Courses.get(c.courseToKey());

            if (hasCourse != null) {
                sharedCourses.add(hasCourse);
            }
        }

        return sharedCourses; // TODO: check if empty, if so returns NoCourseSharedException
    }

    // EFFECTS: returns <firstName> <lastName>
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // EFFECTS: returns the courses from courseList, as opposed to the entire HashMap.
    public Collection<Course> getCourses() {
        return courseList.values();
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
