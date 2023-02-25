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
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.student = new Student(username, firstName, lastName);
        // stub
    }

    // EFFECTS: checks if the password matches the account.
    public Student login(String pw) {
        if (this.password.equals(pw)) {
            return this.student; // stub
        } else {
            return null; // TODO: throw IncorrectPasswordException
        }
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

    public Student getStudent() {
        return student;
    }
}