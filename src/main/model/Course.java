package model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseName;
    private Integer courseNum;
    private Integer courseSection;
    private List<Student> studentList;

    public Boolean addStudent(Student student) {
        return false; // stub
    }

    public Boolean removeStudent(Student student) {
        return false; // stub
    }

    public Boolean searchStudent(Student student) {
        return false; // stub
    }


    public List<Student> getStudentList() {
        return studentList;
    }
}
