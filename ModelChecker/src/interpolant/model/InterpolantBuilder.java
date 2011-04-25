package interpolant.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import proof.model.*;
import proof.model.Variable;
import logic.model.*;
import logic.printer.NameMap;

public class InterpolantBuilder extends ProofTraverser{
	NameMap nameMap;
	Set<RootClause> clausesA;
	Set<proof.model.Variable> variablesB;
	HashMap<proof.model.Clause, Expression> partialInterpolants;
	
	public void setNameMap(NameMap nameMap)
	{
		this.nameMap= nameMap;	
	}
	
	public void setAExpression(Expression e)
	{
		this.clausesA = new HashSet<RootClause>();
		ProofClauseExtractor pce = new ProofClauseExtractor(this.clausesA);
		pce.setNameMap(this.nameMap);
		pce.visitFull(e);
	}
	
	public void setBExpression(Expression e)
	{
		this.variablesB = new HashSet<proof.model.Variable>();
		ProofVariableExtractor pve = new ProofVariableExtractor(this.variablesB);
		pve.setNameMap(this.nameMap);
		pve.visitFull(e);
	}
	
	public void chain(ChainClause clause)
	{
		if(this.partialInterpolants.containsKey(clause))
			return;
		
		Iterator<Clause> it_clause= clause.clauses.iterator(); 
		Iterator<Variable> it_pivots= clause.pivots.iterator();
		
		Clause first = it_clause.next();
		first.traverse(this);
		
		Expression current = this.partialInterpolants.get(first);
		while(it_clause.hasNext())
		{
			current = this.getPartialInterpolant(current, it_pivots.next(), it_clause.next());
		}
		
		this.partialInterpolants.put(clause, current);
	}
	
	public Expression getPartialInterpolant(Expression old, Variable pivot, Clause next)
	{
		next.traverse(this);
		
		BinaryOp ret = new BinaryOp();
		ret.setTheLHS(old);
		ret.setTheRHS(this.partialInterpolants.get(next));
		if(this.variablesB.contains(pivot))
		{
			ret.setTheBinaryOperator(BinaryOperator.AND);
		}
		else
		{
			ret.setTheBinaryOperator(BinaryOperator.OR);
		}
		return ret;
	}
	
	public Expression getExpressionForLiteral(Literal lit)
	{
		if(lit.isNegative())
		{
			UnaryOp o = new UnaryOp();
			o.setTheOperator(UnaryOperator.NOT);
			logic.model.Variable var = new logic.model.Variable();
			var.setTheName(this.nameMap.getVarName(lit.getVariableNo()));
			o.setTheExpression(var);
			return o;
		}
		else
		{
			logic.model.Variable var = new logic.model.Variable();
			var.setTheName(this.nameMap.getVarName(lit.getVariableNo()));
			return var;
		}
	}
	
	public void root(RootClause clause)
	{
		if(this.partialInterpolants.containsKey(clause))
			return;
		
	   Expression e;
	   if(this.clausesA.contains(clause))
	   {
		   Iterator<Literal> it_literals = clause.getLiterals().iterator();
		   HashSet<Literal> partial_int_lits = new HashSet<Literal>();
		   while(it_literals.hasNext())
		   {
			 Literal elem = it_literals.next();
			 if(variablesB.contains(elem.getVariable()))
			 {
				 partial_int_lits.add(elem);
			 }
		   }
		   

		   if(partial_int_lits.size()>1)
		   {
			   BinaryOp o = new BinaryOp();
			   Iterator<Literal> it_partials = partial_int_lits.iterator();
			   o.setTheBinaryOperator(BinaryOperator.OR);
			   o.setTheLHS(this.getExpressionForLiteral(it_partials.next()));
			   Literal last = it_partials.next();
			   
			   BinaryOp latest = o;
			   while(it_partials.hasNext())
			   {
				   BinaryOp newest = new BinaryOp();
				   newest.setTheBinaryOperator(BinaryOperator.OR);
				   newest.setTheLHS(this.getExpressionForLiteral(it_partials.next()));
				   latest.setTheRHS(newest);
				   latest = newest;
			   }
			   latest.setTheRHS(this.getExpressionForLiteral(last));			   
			   e = o;
		   }
		   else if(partial_int_lits.size() == 1)
		   {
			   Iterator<Literal> it_partials = partial_int_lits.iterator();
			   e = this.getExpressionForLiteral(it_partials.next());
		   }
		   else
		   {
			   e = new TrueLiteral();
		   }
	   }
	   else
	   {
		   e = new TrueLiteral();
	   }
	   this.partialInterpolants.put(clause, e);
	}
	
}
