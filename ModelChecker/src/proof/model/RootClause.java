package proof.model;

import java.util.ArrayList;

public class RootClause extends Clause {
	ArrayList<Literal> literals;
	
	public RootClause() {
		this.literals = new ArrayList<Literal>();
	}
	
	public void addLiteral(Literal l)
	{
		this.literals.add(l);
	}

}
