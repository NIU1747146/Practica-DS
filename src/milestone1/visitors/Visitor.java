package milestone.n2.visitors;

import milestone.n2.Partition;
import milestone.n2.Space;

public interface Visitor {

  void visitSpace(Space space);

  void visitPartition(Partition partition);
}
