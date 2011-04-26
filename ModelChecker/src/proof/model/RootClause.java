package proof.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RootClause extends Clause {
	HashSet<Literal> literals;
	
	public RootClause() {
		this.literals = new HashSet<Literal>();
	}
	
	public void addLiteral(Literal l)
	{
		this.literals.add(l);
	}
	
	@Override
	public Set<Literal> getLiterals()
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
	
	public String toString()
	{
		return this.literals.toString();
	}
	
	public void traverse(ProofTraverser r)
	{
		r.root(this);
	}
	
    @Override public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof RootClause) {
            RootClause that = (RootClause) other;
            result = this.literals.equals(that.literals);
        }
        return result;
    }

    @Override public int hashCode() {
        return this.literals.hashCode();
    }

}
