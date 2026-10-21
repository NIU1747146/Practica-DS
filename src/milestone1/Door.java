package milestone1;

import milestone1.doorstates.DoorState;
import milestone1.doorstates.States;
import milestone1.doorstates.Unlocked;
import milestone1.requests.RequestReader;
import org.json.JSONObject;

// Door of the building; delegates each action to its current state (State pattern).
public class Door {
  private final String id;
  private boolean closed; // physically
  private DoorState doorState;
  private Space originSpace;
  private Space destinationSpace;

  public Door(String id) {
    this.id = id;
    closed = true;
    this.doorState = new Unlocked(this, States.UNLOCKED);
  }

  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        doorState.open();
        break;
      case Actions.CLOSE:
        doorState.close();
        break;
      case Actions.LOCK:
        doorState.lock();
        break;
      case Actions.UNLOCK:
        doorState.unlock();
        break;
      case Actions.UNLOCK_SHORTLY:
        doorState.unlockShortly();
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public boolean isClosed() {
    return closed;
  }

  public void setClosed(boolean closed) {
    this.closed = closed;
  }

  public String getId() {
    return id;
  }

  public String getStateName() {
    return doorState.getName();
  }

  public Space getOriginSpace() {
    return originSpace;
  }

  public Space getDestinationSpace() {
    return destinationSpace;
  }

  public void setSpaces(Space origin, Space destination) {
    this.originSpace = origin;
    this.destinationSpace = destination;
  }

  // called by the states to change the state
  public void setState(DoorState state) {
    this.doorState = state;
  }

  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    json.put("originSpace", originSpace.getId());
    json.put("destinationSpace", destinationSpace.getId());
    return json;
  }
}
