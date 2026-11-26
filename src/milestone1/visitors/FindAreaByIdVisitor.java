package milestone1.visitors;

import milestone1.Area;
import milestone1.Partition;
import milestone1.Space;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Visitor that finds an area by its ID.
 */
public class FindAreaByIdVisitor implements Visitor {
  private static final Logger logger = LoggerFactory.getLogger(FindAreaByIdVisitor.class);

  private final String id;
  private Area resultArea = null;

  public FindAreaByIdVisitor(String idToFind) {
    this.id = idToFind;
  }

  @Override
  public void visitSpace(Space space) {
    if (space.getId().equals(id)) {
      this.resultArea = space;
      logger.debug("Found space " + id);
    }
  }

  @Override
  public void visitPartition(Partition partition) {
    if (partition.getId().equals(id)) {
      this.resultArea = partition;
      logger.debug("Found partition " + id);
    }
  }

  public Area getResultArea() {
    return resultArea;
  }
}
