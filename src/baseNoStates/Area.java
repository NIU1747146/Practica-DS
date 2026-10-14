package baseNoStates;

import java.util.ArrayList;

// An Area is a place of the building where access can be controlled: either a Space (a room,
// the parking, the stairs...) or a Partition (a group of spaces and/or other partitions, like a
// floor). The administrator grants permissions and sends lock/unlock requests to areas, never
// directly to doors. Area is the Component of the Composite design pattern: clients (area
// requests, user groups) treat a single space and a whole partition in the same way, without
// knowing which one they have.
public abstract class Area {
  protected final String id;

  protected Area(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  // doors whose destination is this area (a space) or any space inside it (a partition)
  public abstract ArrayList<Door> getDoorsGivingAccess();

  // all the spaces of this area (itself if it is a space), used to know where a user can be
  public abstract ArrayList<Space> getSpaces();

  // returns null if neither this area nor anything below it has that id
  public abstract Area findAreaById(String id);
}
