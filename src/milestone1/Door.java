package milestone1;

import milestone1.doorstates.DoorState;
import milestone1.doorstates.States;
import milestone1.doorstates.Unlocked;
import milestone1.requests.RequestReader;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Delegates door actions to its current state (State pattern).
 */
public class Door {
  private static final Logger logger = LoggerFactory.getLogger(Door.class);

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
    if (request.isAuthorized()) {
      String action = request.getAction();
      logger.debug("Door " + id + " in state " + getStateName() + " does action " + action);
      doAction(action);
    } else {
      logger.debug("Door " + id + ": request not authorized");
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

  public DoorState getState() {
    return doorState;
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
