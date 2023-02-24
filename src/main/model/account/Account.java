package model.account;

public class Account {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Student student;

    // EFFECTS: creates a new account with username, first and last name, and password,
    //          then creates a new Student inside the Account.
    public Account(String username, String firstName, String lastName, String password) {
        // stub
    }

    // EFFECTS: checks if the password matches the account.
    public Student login(String pw) {
        return null; // stub
    }

}
