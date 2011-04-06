package proof.model;

import java.util.ArrayList;
import java.util.Iterator;

public class RootClause extends Clause {
	ArrayList<Literal> literals;
	
	public RootClause() {
		this.literals = new ArrayList<Literal>();
	}
	
	public void addLiteral(Literal l)
	{
		this.literals.add(l);
	}
	
	@Override
	public ArrayList<Literal> getLiterals()
	{
		return this.literals;
	}

	@Override
	void print() {
		System.out.print("Root: ");
		Iterator<Literal> it = this.literals.iterator();
		while(it.hasNext())
		{
			System.out.print(it.next().toString()+" ");
		}
		System.out.print("\n");
	}
	
	void traverse(ProofTraverser r)
	{
		r.root(this);
	}

}
