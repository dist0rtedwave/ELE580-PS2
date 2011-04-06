package proof.model;

abstract public class ProofTraverser {	
	abstract void chain(ChainClause clause);
	abstract void root(RootClause clause);
}
