package model.account;

import model.Event;
import model.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.HashMap;
import java.util.Map;

// Represents an object containing all user accounts in the applet session.
public class AllAccounts implements Writable {
    private Map<String, Account> accountList;

    // EFFECTS: initializes AllAccounts with an empty list of accounts.
    public AllAccounts() {
        accountList = new HashMap<>();
    }

    // EFFECTS: searches map for username key: if value is found, calls method to attempt sign in.
    // Returns a Student if successful, null if not.
    public Student login(String username, String password) {
        // get method, then set as student and use Account.login
        Account wantedAccount = accountList.get(username);
        if (wantedAccount != null) {
            return wantedAccount.login(password); // TODO: might also throw IncorrectPasswordException
        } else {
            EventLog.getInstance().logEvent(new Event("⚠️ Account " + username + " was not found."));
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
        EventLog.getInstance().logEvent(new Event("✅ Signup for " + username + " was successful."));
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

    public Map<String, Account> getAccountList() {
        return accountList;
    }

    // EFFECTS: returns AllAccounts as a JSON object.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Account a : accountList.values()) {
            jsonArray.put(a.toJson());
        }

        json.put("accountList", jsonArray);
        return json;
    }
}
