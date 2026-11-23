package milestone1.requests;

import java.util.ArrayList;
import milestone1.DirectoryDoors;
import milestone1.Door;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Request to get the state of all the doors.
 */
public class RequestRefresh implements Request {
  private final ArrayList<JSONObject> jsonsDoors = new ArrayList<>();

  // Retrieves the state of all doors to update the simulator when the page loads
  // or when doors change between locked and propped states after a refresh request.
  // Also allows testing partition requests sent by the Flutter client app
  // and updating the simulator's readers accordingly.
  public void process() {
    for (Door door : DirectoryDoors.getInstance().getAllDoors()) {
      jsonsDoors.add(door.toJson());
    }
  }

  @Override
  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("doors", new JSONArray(jsonsDoors));
    // jsonDoors has been set previously by process()
    return json;
  }

  @Override
  public String toString() {
    return "RequestRefresh{"
            + jsonsDoors
            + "}";
  }
}
