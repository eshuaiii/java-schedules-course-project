package model.account;

import java.util.HashMap;

public class AllAccounts {
    private HashMap<String, Account> accountList;

    // EFFECTS: initializes AllAccounts with an empty list of accounts.
    public AllAccounts() {
        accountList = new HashMap<>();
    }

    // EFFECTS: goes through the entire list of accounts to see if one matches username: if so, calls method
    //          to attempt sign in. Returns a Student if successful, null if not.
    public Student login(String username, String password) {
        // get method, then set as student and use Account.login
        Account wantedAccount = accountList.get(username);
        if (wantedAccount != null) {
            return wantedAccount.login(password); // TODO: might also throw IncorrectPasswordException
        } else {
            return null; // TODO: throw AccountNotFoundException
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new Account, adds to studentList, and returns its Student object
    public Student signup(String username, String firstName, String lastName, String password) {
        if (accountList.get(username) != null) {
            return null;
        }
        Account newAccount = new Account(username, firstName, lastName, password);
        accountList.put(username, newAccount);
        return newAccount.getStudent();
    }

    // EFFECTS: given a username, searches for a student in accountList.
    //          If found, returns the Student, null otherwise.
    public Student searchStudent(String username) {
        Account requiredAccount = accountList.get(username);

        if (requiredAccount == null) {
            return null; // TODO: throw StudentNotFoundException
        } else {
            return requiredAccount.getStudent();
        }
    }

    public HashMap<String, Account> getAccountList() {
        return accountList;
    }
}
