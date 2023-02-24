package model.account;

import model.course.Course;

import java.util.ArrayList;
import java.util.List;

public class AllAccounts {
    List<Account> accountList;

    // ???
    public AllAccounts() {
        // stub
    }

    // checks all students and attempts to log in
    public Student login(String username, String password) {
        // get method, then set as student and use Account.login
        return new Student("a", "b"); // stub
    }

    // creates a new student...idk what I need here
    public Student signup(String username, String firstName, String lastName, String password) {
        // run new Account(), then add to the list
        return new Student("a", "b"); // stub
    }

    // view your classmates
    public List<Student> viewClassmates(Course course) {
        // for every element in all account, run another foreach loop to check each course. I love nested loops
        return new ArrayList<Student>();
    }

    // view courses given student
    // not sure if first name last name or username yet
    public List<Course> searchStudent() {
        return new ArrayList<Course>();
    }

    // view courses shared with a student
    // not sure if first name last name or username yet
    public List<Course> seeSharedCourses() {
        // parse list to find student, then pick out list to compare
        return new ArrayList<Course>();
    }
}
