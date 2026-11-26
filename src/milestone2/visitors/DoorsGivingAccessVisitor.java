package milestone2.visitors;

import java.util.ArrayList;
import java.util.List;
import milestone1.Door;
import milestone1.Partition;
import milestone1.Space;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Visitor that collects all doors giving access to a space.
 */
public class DoorsGivingAccessVisitor implements Visitor {
  private static final Logger logger = LoggerFactory.getLogger(DoorsGivingAccessVisitor.class);

  private final List<Door> doors = new ArrayList<>();

  @Override
  public void visitSpace(Space space) {
    logger.debug("Visiting space " + space.getId() + " to collect its doors");
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
