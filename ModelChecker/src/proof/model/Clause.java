package proof.model;

import java.util.ArrayList;
import java.util.Set;

abstract public class Clause {
   abstract void print();
   abstract Set<Literal> getLiterals();
   abstract public void traverse(ProofTraverser r);
}
