package milestone1;

import java.util.ArrayList;

// Space or partition of the building (Component of the Composite pattern).
public abstract class Area {
  protected final String id;

  protected Area(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  // doors whose destination is a space of this area
  public abstract ArrayList<Door> getDoorsGivingAccess();

  // all the spaces of this area
  public abstract ArrayList<Space> getSpaces();

  // null if not found
  public abstract Area findAreaById(String id);
}
