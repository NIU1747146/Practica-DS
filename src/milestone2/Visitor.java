// Package name contains digits (milestone1/milestone2) as required by the project specification
package milestone2;

import milestone1.Partition;
import milestone1.Space;

// Visitor.java
// Visitor of the tree of areas (Visitor pattern). Each traversal (find an area, collect doors,
// collect spaces) is a class implementing this, so Space and Partition do not change when a new
// traversal is needed. Partition decides the order; the visitor says what to do with each node.
public interface Visitor {

  void visitSpace(Space space);

  void visitPartition(Partition partition);
}
