package milestone1;

import milestone2.Visitor;

// Node of the tree that models the building (Composite pattern): Space is a leaf and Partition
// groups spaces and other partitions, so requests and user groups treat both the same way. The
// traversals of the tree are not here but in the visitors (Visitor pattern); accept() is the
// only entry point.
public abstract class Area {
  protected final String id;

  protected Area(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public abstract void accept(Visitor visitor);
}
