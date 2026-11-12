package milestone1;

import milestone1.visitors.Visitor;

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

  public ArrayList<Door> getDoorsGivingAccess() {
    return doorsGivingAccess;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitSpace(this);
  }
}
