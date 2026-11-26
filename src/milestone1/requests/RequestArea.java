package milestone1.requests;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import milestone1.Actions;
import milestone1.Area;
import milestone1.DirectoryAreas;
import milestone1.Door;
import milestone2.DoorsGivingAccessVisitor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// RequestArea.java
// Lock or unlock request on a whole area. It does not act on doors itself: it finds with a visitor
// the doors giving access to the area and makes one RequestReader per door, so each door is
// authorized separately.
public class RequestArea implements Request {
  private static final Logger logger = LoggerFactory.getLogger(RequestArea.class);

  private final String credential;
  private final String action;
  private final String areaId;
  private final LocalDateTime now;
  private final List<RequestReader> requests = new ArrayList<>();

  public RequestArea(String credential, String action, LocalDateTime now, String areaId) {
    assert action.equals(Actions.LOCK) || action.equals(Actions.UNLOCK)
            : "invalid action " + action + " for an area request";
    this.credential = credential;
    this.action = action;
    this.now = now;
    this.areaId = areaId;
  }

  public String getAction() {
    return action;
  }

  // Processing an area request creates the corresponding door requests and forwards
  // them to all its doors. Only authorized requests will trigger an action.
  @Override
  public void process() {
    Area area = DirectoryAreas.getInstance().findAreaById(areaId);

    if (area != null) {
      // area is null when an action is triggered from the Flutter app without selecting
      // a place, as all parameters are not enforced like in the web simulator.

      // Find all doors giving access to the spaces in this area and process each request.
      DoorsGivingAccessVisitor visitor = new DoorsGivingAccessVisitor();
      area.accept(visitor);
      logger.debug("Area " + areaId + " has " + visitor.getDoors().size() + " doors");

      for (Door door : visitor.getDoors()) {
        RequestReader requestReader = new RequestReader(credential, action, now, door.getId());
        requestReader.process();
        // After process(), each request holds its individual answer for the simulator/app.
        requests.add(requestReader);
      }
    } else {
      logger.warn("Area " + areaId + " not found");
    }
  }

  @Override
  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("action", action);
    json.put("areaId", areaId);

    JSONArray jsonRequests = new JSONArray();
    for (RequestReader requestReader : requests) {
      jsonRequests.put(requestReader.answerToJson());
    }

    json.put("requestsDoors", jsonRequests);
    return json;
  }

  @Override
  public String toString() {
    String requestsDoorsStr;
    if (requests.isEmpty()) {
      requestsDoorsStr = "";
    } else {
      requestsDoorsStr = requests.toString();
    }

    return "Request{"
            + "credential=" + credential
            + ", action=" + action
            + ", now=" + now
            + ", areaId=" + areaId
            + ", requestsDoors=" + requestsDoorsStr
            + "}";
  }
}