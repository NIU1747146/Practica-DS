package milestone1;

import milestone1.visitors.Visitor;

// Space or partition of the building (Component of the Composite pattern).
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
