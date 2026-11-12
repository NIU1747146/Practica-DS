package milestone1.visitors;

import milestone1.Partition;
import milestone1.Space;

public interface Visitor {
  void visitSpace(Space space);
  void visitPartition(Partition partition);
}
