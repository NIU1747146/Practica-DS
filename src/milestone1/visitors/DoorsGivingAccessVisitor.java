package milestone1.visitors;

import milestone1.Door;
import milestone1.Partition;
import milestone1.Space;

import java.util.ArrayList;
import java.util.List;

public class DoorsGivingAccessVisitor implements Visitor {
  private final List<Door> doors = new ArrayList<>();

  @Override
  public void visitSpace(Space space) {
    for (Door door : space.getDoorsGivingAccess()) {
      if (!doors.contains(door)) {
        doors.add(door);
      }
    }
  }

  @Override
  public void visitPartition(Partition partition) {}

  public List<Door> getDoors() {
    return doors;
  }
}
