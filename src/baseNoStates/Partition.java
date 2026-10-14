package baseNoStates;

import java.util.ArrayList;

// A Partition is a node of the tree of areas that groups spaces and/or other partitions (a floor,
// the whole building...). It is the Composite of the Composite design pattern: it implements the
// operations of Area by forwarding them to its children and combining the results, so that new
// groupings can be made without changing the clients.
public class Partition extends Area {
  // spaces and/or other partitions
  private final ArrayList<Area> children = new ArrayList<>();

  public Partition(String id) {
    super(id);
  }

  public void addChild(Area child) {
    children.add(child);
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> doors = new ArrayList<>();
    for (Area child : children) {
      doors.addAll(child.getDoorsGivingAccess());
    }
    return doors;
  }

  @Override
  public ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>();
    for (Area child : children) {
      spaces.addAll(child.getSpaces());
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
