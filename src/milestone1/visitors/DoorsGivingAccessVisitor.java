package milestone.n2.visitors;

import java.util.ArrayList;
import java.util.List;
import milestone.n2.Door;
import milestone.n2.Partition;
import milestone.n2.Space;

/**
 * Visitor that collects all doors giving access to a space.
 */
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
