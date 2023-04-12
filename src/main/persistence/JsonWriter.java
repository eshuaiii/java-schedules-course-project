package persistence;

import model.Event;
import model.EventLog;
import model.account.AllAccounts;
import org.json.JSONObject;


import java.io.*;

// Represents a writer that writes JSON representation of the current program state to file
// modelled based on the JsonSerializationDemo file, https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of AllAccounts to file
    // Note: AllCourses is not part of the JSON generating as it would result in a mutual reference infinite loop.
    public void write(AllAccounts aa) {
        JSONObject json = aa.toJson();
        saveToFile(json.toString(TAB));
        EventLog.getInstance().logEvent(new Event("📜 Saved the session to " + destination));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
