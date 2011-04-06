package proof.model;

import java.util.ArrayList;
import java.util.Iterator;

public class IdentifierPrinterTraverser extends ProofTraverser{	
	
	void printLiterals(ArrayList<Literal> literals)
	{
		if(literals.size()==0)
		{
			System.out.print("[empty]");
		}
		Iterator<Literal> it_literal;
		it_literal = literals.iterator();
		while(it_literal.hasNext()) {System.out.print(" "+it_literal.next().toString());}
	}
	
	void printVariable(Variable var)
	{
		System.out.print(var.toString());
	}
		
	void chain(ChainClause clause)
	{
		System.out.println("Start of Chain Clause");
		Iterator<Clause> it_clause= clause.clauses.iterator(); 
		Iterator<Variable> it_pivots= clause.pivots.iterator();
		
		ArrayList<Literal> literals = it_clause.next().getLiterals();
		while(it_clause.hasNext())
		{
			System.out.print("Right: ");
			printLiterals(literals);
			System.out.print(" Left: ");
			printLiterals(it_clause.next().getLiterals());
            System.out.print(" Pivot: ");
            printVariable(it_pivots.next());
            System.out.print("\n");
		}
		System.out.print("Final Literals: ");
		printLiterals(clause.getLiterals());
		System.out.println("\nEnd of Chain Clause");	
	}
	
	
	void root(RootClause clause)
	{
		System.out.print("Root: ");
		printLiterals(clause.getLiterals());
		System.out.print("\n");
	}
}
