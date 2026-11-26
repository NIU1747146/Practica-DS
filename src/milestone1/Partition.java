package milestone1;

import java.util.ArrayList;
import milestone2.visitors.Visitor;

/**
 * Group of spaces and/or partitions (Composite of the Composite pattern).
 */
public class Partition extends Area {
  // spaces and/or other partitions
  private final ArrayList<Area> children;

  public Partition(String id) {
    super(id);
    this.children = new ArrayList<>();
  }

  public void addChild(Area child) {
    children.add(child);
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitPartition(this);

    for (Area child : children) {
      child.accept(visitor);
    }
  }
}