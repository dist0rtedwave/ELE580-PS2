package proof.model;

import java.util.ArrayList;

public class ChainClause extends Clause {
	ArrayList<Clause> clauses;
	ArrayList<Variable> pivots;
	
	public ChainClause() {
		this.clauses = new ArrayList<Clause>();
		this.pivots = new ArrayList<Variable>();
	}
	
	public ChainClause(ArrayList<Clause> resolution_clauses, ArrayList<Variable> pivots) {
		assert(cs.size() == ps.size()+1);
		this.clauses = resolution_clauses;
		this.pivots = pivots;
	}
}
