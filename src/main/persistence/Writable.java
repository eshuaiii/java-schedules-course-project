package persistence;

import org.json.JSONObject;

// modelled based on the JsonSerializationDemo file, https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// interface to encapsulate all classes that can be added to the JSON file.
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
