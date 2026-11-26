package milestone1.requests;

import java.util.ArrayList;
import milestone1.DirectoryDoors;
import milestone1.Door;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Request for the state of all the doors. The simulator sends it when loading and periodically,
// to redraw the doors that changed by themselves (locked or propped after the time out).
public class RequestRefresh implements Request {
  private final ArrayList<JSONObject> jsonsDoors = new ArrayList<>();
  private static final Logger logger = LoggerFactory.getLogger(RequestRefresh.class);

  // Retrieves the state of all doors to update the simulator when the page loads
  // or when doors change between locked and propped states after a refresh request.
  // Also allows testing partition requests sent by the Flutter client app
  // and updating the simulator's readers accordingly.
  @Override
  public void process() {
    ArrayList<Door> doors = DirectoryDoors.getInstance().getAllDoors();
    logger.debug("Refresh request: {} doors", doors.size());
    for (Door door : doors) {
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
