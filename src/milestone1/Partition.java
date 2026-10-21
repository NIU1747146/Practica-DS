package milestone1;

import java.util.ArrayList;

// Group of spaces and/or partitions (Composite of the Composite pattern).
public class Partition extends Area {
  // spaces and/or other partitions
  private final ArrayList<Area> children;

  public Partition(String id) {
    super(id);
    this.children = new ArrayList<>();
  }

  public void addChild(Area child) {
    children.add(child);
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> doors = new ArrayList<>();
    for (Area child : children) {
      for (Door door : child.getDoorsGivingAccess()) {
        if (!doors.contains(door)) {
          doors.add(door);
        }
      }
    }
    return doors;
  }

  @Override
  public ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>();
    for (Area child : children) {
      for (Space space : child.getSpaces()) {
        if (!spaces.contains(space)) {
          spaces.add(space);
        }
      }
    }
    return spaces;
  }

  @Override
  public Area findAreaById(String id) {
    if (this.id.equals(id)) {
      return this;
    }
    for (Area child : children) {
      Area found = child.findAreaById(id);
      if (found != null) {
        return found;
      }
    }
    return null;
  }
}
