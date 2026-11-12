package milestone1.visitors;

import milestone1.Partition;
import milestone1.Space;

import java.util.ArrayList;

public class SpacesVisitor implements Visitor {
  private final ArrayList<Space> spaces = new ArrayList<>();

  @Override
  public void visitSpace(Space space) {
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
