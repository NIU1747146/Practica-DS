package milestone1;

import java.util.ArrayList;
import milestone2.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Space.java
// Leaf of the Composite: a room or place (parking, hall, stairs...). It knows the doors giving
// access to it, because an area request needs to find the doors of all the spaces of an area.
// DirectoryAreas links each door with its origin and destination spaces.
public class Space extends Area {
  private final ArrayList<Door> doorsGivingAccess;
  private static final Logger logger = LoggerFactory.getLogger(Space.class);

  public Space(String id) {
    super(id);
    this.doorsGivingAccess = new ArrayList<>();
  }

  public void addDoorGivingAccess(Door door) {
    doorsGivingAccess.add(door);
    logger.debug("Door {} gives access to space {}", door.getId(), id);
  }

  public ArrayList<Door> getDoorsGivingAccess() {
    return doorsGivingAccess;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitSpace(this);
  }
}
