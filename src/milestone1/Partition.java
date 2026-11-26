package milestone1;

import java.util.ArrayList;
import milestone2.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Composite of the Composite: a group of spaces and/or other partitions (a floor, the basement,
// the whole building). Users are given areas that can be partitions, so permissions and area
// requests act on all the spaces below.
public class Partition extends Area {
  // spaces and/or other partitions
  private final ArrayList<Area> children;
  private static final Logger logger = LoggerFactory.getLogger(Partition.class);

  public Partition(String id) {
    super(id);
    this.children = new ArrayList<>();
  }

  public void addChild(Area child) {
    children.add(child);
  }

  // pre-order: the partition itself first, then its children
  @Override
  public void accept(Visitor visitor) {
    logger.debug("Visiting partition {} with {} children", id, children.size());
    visitor.visitPartition(this);

    for (Area child : children) {
      child.accept(visitor);
    }
  }
}