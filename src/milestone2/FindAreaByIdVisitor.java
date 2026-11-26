package milestone2;

import milestone1.Area;
import milestone1.Partition;
import milestone1.Space;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// FindAreaByIdVisitor.java
// Looks for the area (space or partition) with a given id, null if there is none. It turns the
// ids of requests and groups into an Area. Ids are unique, so it does not stop at the first match.
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
