package milestone1.visitors;

import java.util.ArrayList;
import milestone1.Partition;
import milestone1.Space;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Visitor that collects all spaces in a list.
 */
public class SpacesVisitor implements Visitor {
  private static final Logger logger = LoggerFactory.getLogger(SpacesVisitor.class);

  private final ArrayList<Space> spaces = new ArrayList<>();

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
