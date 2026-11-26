package milestone1.requests;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import milestone1.DirectoryDoors;
import milestone1.DirectoryUsers;
import milestone1.Door;
import milestone1.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Request made at the reader of one door: who (credential), what (action), when and where (door).
// process() authorizes the user and tells the Door to do the action. The answer holds the reasons
// why it was not authorized.
public class RequestReader implements Request {
  private static final Logger logger = LoggerFactory.getLogger(RequestReader.class);

  private final String credential; // who
  private final String action;     // what
  private final LocalDateTime now; // when
  private final String doorId;     // where
  private String userName;
  private boolean authorized;
  private final List<String> reasons; // why not authorized
  private String doorStateName;
  private boolean doorClosed;

  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    this.credential = credential;
    this.action = action;
    this.now = now;
    this.doorId = doorId;
    this.reasons = new ArrayList<>();
  }

  public void setDoorStateName(String name) {
    doorStateName = name;
  }

  public String getAction() {
    return action;
  }

  public boolean isAuthorized() {
    return authorized;
  }

  public void addReason(String reason) {
    reasons.add(reason);
  }

  // Sets the authorization result and, if denied, records the reasons for testing.
  private void authorize(User user, Door door) {
    if (user == null) {
      authorized = false;
      addReason("user doesn't exist");
      return;
    }

    userName = user.getName();
    boolean isAuthorized = true;

    if (!user.canSendRequest(now)) {
      isAuthorized = false;
      addReason("Present date and/or time not in schedule");
    }

    if (user.cannotAccessSpace(door.getOriginSpace())) {
      isAuthorized = false;
      addReason("Not allowed origin " + door.getOriginSpace().getId());
    }

    if (user.cannotAccessSpace(door.getDestinationSpace())) {
      isAuthorized = false;
      addReason("Not allowed destination " + door.getDestinationSpace().getId());
    }

    if (!user.canDoAction(action)) {
      isAuthorized = false;
      addReason("Not allowed action " + action);
    }

    authorized = isAuthorized;
  }

  @Override
  public void process() {
    User user = DirectoryUsers.getInstance().findUserByCredential(credential);
    Door door = DirectoryDoors.getInstance().findDoorById(doorId);
    assert door != null : "door " + doorId + " not found";

    authorize(user, door);
    logger.debug("Request of " + userName + " to " + action + " door " + doorId
        + " authorized " + authorized + " reasons " + reasons);
    // Even if not authorized we process the request so all requests can be logged.
    door.processRequest(this);

    doorClosed = door.isClosed();
  }

  @Override
  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("authorized", authorized);
    json.put("action", action);
    json.put("doorId", doorId);
    json.put("closed", doorClosed);
    json.put("state", doorStateName);
    json.put("reasons", new JSONArray(reasons));
    return json;
  }

  @Override
  public String toString() {
    if (userName == null) {
      userName = "unknown";
    }

    return "Request{"
            + "credential=" + credential
            + ", userName=" + userName
            + ", action=" + action
            + ", now=" + now
            + ", doorID=" + doorId
            + ", closed=" + doorClosed
            + ", authorized=" + authorized
            + ", reasons=" + reasons
            + "}";
  }
}