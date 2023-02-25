package model.account;

import model.course.Course;

import java.util.ArrayList;
import java.util.List;

public class AllAccounts {
    // TODO: consider implementing a hashmap (dictionary) to make this easier to search for?
    List<Account> accountList;

    // EFFECTS: initializes AllAccounts with an empty list of accounts.
    public AllAccounts() {
        accountList = new ArrayList<Account>();
    }

    // EFFECTS: goes through the entire list of accounts to see if one matches username: if so, calls method
    //          to attempt sign in. Returns a Student if successful, null if not.
    // checks all students and attempts to log in
    public Student login(String username, String password) {
        // get method, then set as student and use Account.login
        Account wantedAccount = null;
        for (Account s : this.accountList) {
            if (username == s.getUsername()) {
                wantedAccount = s;
                break;
            }
        }

        if (wantedAccount != null) {
            return wantedAccount.login(password);
        } else {
            return null; // TODO: throw AccountNotFoundException
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new Account, adds to studentList, and returns its Student object
    public Student signup(String username, String firstName, String lastName, String password) {
        Account newAccount = new Account(username, firstName, lastName, password);
        accountList.add(newAccount);
        return newAccount.getStudent();
    }

    // TODO
    // view your classmates
    public List<Student> viewClassmates(Course course) {
        // for every element in all account, run another foreach loop to check each course. I love nested loops
        return new ArrayList<Student>();
    }

    // TODO
    // view courses given student
    // not sure if first name last name or username yet
    public List<Course> searchStudent() {
        return new ArrayList<Course>();
    }

    // TODO
    // view courses shared with a student
    // not sure if first name last name or username yet
    public List<Course> seeSharedCourses() {
        // parse list to find student, then pick out list to compare
        return new ArrayList<Course>();
    }
}
