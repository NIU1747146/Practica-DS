package milestone.n2.visitors;

import java.util.ArrayList;
import milestone.n2.Partition;
import milestone.n2.Space;

/**
 * Visitor that collects all spaces in a list.
 */
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
