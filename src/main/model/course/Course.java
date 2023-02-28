package model.course;

import model.account.Student;

import java.util.*;

// Represents a course with its shorthand name, its course number and section, and a list of the students in the course.
public class Course {
    private String courseName;
    private Integer courseNum;
    private Integer courseSection;
    private Collection<Student> studentList;

    // EFFECTS: creates a new course given its name, number, and section, then creates an empty studentList.
    public Course(String courseName, Integer courseNum, Integer courseSection) {
        this.courseName = courseName;
        this.courseNum = courseNum;
        this.courseSection = courseSection;
        studentList = new HashSet<>();
    }

    // REQUIRES: student is not already in studentList.
    // MODIFIES: this
    // EFFECTS: adds a student to the course's studentList.
    // TODO: Should I also modify student again here? Is public a bad idea?
    public Boolean addStudent(Student student) {
        return studentList.add(student);
    }

    // REQUIRES: student is in studentList.
    // MODIFIES: this
    // EFFECTS: removes a student from the course's studentList
    // TODO: Should I also modify student again here? Is public a bad idea?
    public Boolean removeStudent(Student student) {
        return studentList.remove(student);
    }

    // EFFECTS: returns a sorted list of studentList in string form
    public List<String> getStudentListSorted() {
        List<String> studentArrayList = new ArrayList<>();

        for (Student s : studentList) {
            studentArrayList.add(s.getFullName());
        }
        Collections.sort(studentArrayList);

        return studentArrayList;
    }

    // EFFECTS: returns a concatenated string of the form <courseName>-<courseNum>-<courseSection>
    public String courseToKey() {
        return this.getCourseName() + "-" + this.getCourseNum() + "-" + this.getCourseSection();
    }

    // EFFECTS: returns a long string of the form <courseName> <courseNum>, Section <courseSection>
    public String courseLong() {
        return this.getCourseName() + " " + this.getCourseNum() + ", Section " + this.getCourseSection();
    }

    public String getCourseName() {
        return courseName;
    }

    public Integer getCourseNum() {
        return courseNum;
    }

    public Integer getCourseSection() {
        return courseSection;
    }

    public Collection<Student> getStudentList() {
        return studentList;
    }
}
