package milestone1.visitors;

import milestone1.Area;
import milestone1.Partition;
import milestone1.Space;

/**
 * Visitor that finds an area by its ID.
 */
public class FindAreaByIdVisitor implements Visitor {
  private final String id;
  private Area resultArea = null;

  public FindAreaByIdVisitor(String idToFind) {
    this.id = idToFind;
  }

  @Override
  public void visitSpace(Space space) {
    if (space.getId().equals(id)) {
      this.resultArea = space;
    }
  }

  @Override
  public void visitPartition(Partition partition) {
    if (partition.getId().equals(id)) {
      this.resultArea = partition;
    }
  }

  public Area getResultArea() {
    return resultArea;
  }
}
