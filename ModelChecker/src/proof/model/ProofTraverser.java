package proof.model;

abstract public class ProofTraverser {	
	abstract public void chain(ChainClause clause);
	abstract public void root(RootClause clause);
}
