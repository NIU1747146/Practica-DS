package baseNoStates;

import java.util.ArrayList;

// A Space is a leaf of the tree of areas: a room, the parking, the stairs, the exterior...
// It knows the doors that give access to it (those having this space as destination) because an
// area request has to be turned into one reader request per door giving access to the area.
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
