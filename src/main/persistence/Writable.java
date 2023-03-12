package persistence;

import org.json.JSONObject;

// modelled based on the JsonSerializationDemo file, https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// JSON library based from: https://github.com/stleary/JSON-java
// interface to encapsulate all classes that can be added to the JSON file.
public interface Writable {

    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
