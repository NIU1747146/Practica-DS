package milestone1.requests;

import java.time.LocalDateTime;
import java.util.ArrayList;
import milestone1.DirectoryDoors;
import milestone1.DirectoryUsers;
import milestone1.Door;
import milestone1.User;
import org.json.JSONArray;
import org.json.JSONObject;

// Request made at the reader of one door.
public class RequestReader implements Request {
  private final String credential; // who
  private final String action;     // what
  private final LocalDateTime now; // when
  private final String doorId;     // where
  private String userName;
  private boolean authorized;
  private final ArrayList<String> reasons; // why not authorized
  private String doorStateName;
  private boolean doorClosed;

  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    this.credential = credential;
    this.action = action;
    this.doorId = doorId;
    reasons = new ArrayList<>();
    this.now = now;
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

  // see if the request is authorized and put this into the request, then send it to the door.
  // if authorized, perform the action.
  public void process() {
    User user = DirectoryUsers.findUserByCredential(credential);
    Door door = DirectoryDoors.findDoorById(doorId);
    assert door != null : "door " + doorId + " not found";
    authorize(user, door);
    // this sets the boolean authorize attribute of the request
    door.processRequest(this);
    // even if not authorized we process the request, so that if desired we could log all
    // the requests made to the server as part of processing the request
    doorClosed = door.isClosed();
  }

  // the result is put into the request object plus, if not authorized, why not,
  // only for testing
  private void authorize(User user, Door door) {
    if (user == null) {
      authorized = false;
      addReason("user doesn't exists");
      return;
    }

    userName = user.getName();
    boolean isAuthorized = true;

    if (!user.canSendRequest(now)) {
      isAuthorized = false;
      addReason("Present date and/or time not in schedule");
    }

    if (!user.canAccessSpace(door.getOriginSpace())) {
      isAuthorized = false;
      addReason("Not allowed origin " + door.getOriginSpace().getId());
    }

    if (!user.canAccessSpace(door.getDestinationSpace())) {
      isAuthorized = false;
      addReason("Not allowed destination " + door.getDestinationSpace().getId());
    }

    if (!user.canDoAction(action)) {
      isAuthorized = false;
      addReason("Not allowed action " + action);
    }

    authorized = isAuthorized;
  }
}

