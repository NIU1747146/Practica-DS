package milestone2;

import java.util.ArrayList;
import milestone1.Partition;
import milestone1.Space;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// SpacesVisitor.java
// Collects the spaces under an area (the area itself if it is a space). UserGroup uses it to know
// if a space is inside one of the areas allowed to the group.
public class SpacesVisitor implements Visitor {
  private static final Logger logger = LoggerFactory.getLogger(SpacesVisitor.class);

  private final ArrayList<Space> spaces = new ArrayList<>();

  // a space can belong to several partitions, so we avoid adding it twice
  @Override
  public void visitSpace(Space space) {
    logger.debug("Visiting space " + space.getId());
    if (!spaces.contains(space)) {
      spaces.add(space);
    }
  }

  @Override
  public void visitPartition(Partition partition) {}

  public ArrayList<Space> getSpaces() {
    return spaces;
  }
}
