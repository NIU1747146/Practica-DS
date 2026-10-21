package milestone1;

import java.util.ArrayList;

// Room or place of the building; knows the doors giving access to it (Leaf of the Composite).
public class Space extends Area {
  private final ArrayList<Door> doorsGivingAccess;

  public Space(String id) {
    super(id);
    this.doorsGivingAccess = new ArrayList<>();
  }

  public void addDoorGivingAccess(Door door) {
    doorsGivingAccess.add(door);
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return doorsGivingAccess;
  }

  @Override
  public ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>();
    spaces.add(this);
    return spaces;
  }

  @Override
  public Area findAreaById(String id) {
    return this.id.equals(id) ? this : null;
  }
}
