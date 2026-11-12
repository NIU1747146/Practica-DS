package milestone1;

import milestone1.visitors.Visitor;

import java.util.ArrayList;

// Group of spaces and/or partitions (Composite of the Composite pattern).
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
