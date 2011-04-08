package logic.converter;

/* will take an expression and create an expression with equivalences that the tseitin2cnf will handle*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import logic.converter.test.CycleChecker;
import logic.model.BinaryOp;
import logic.model.BinaryOperator;
import logic.model.EF;
import logic.model.Expression;
import logic.model.FalseLiteral;
import logic.model.TrueLiteral;
import logic.model.UnaryOp;
import logic.model.Variable;
import logic.model.Visitor;
import logic.printer.PrintVisitor;

public class Converter extends Visitor<ConverterContext> {

	private static boolean validVar(Expression e){
		if(e instanceof Variable){
			return true;
		}
		if(e instanceof UnaryOp){
			if(((UnaryOp) e).getTheExpression() instanceof Variable){
				return true;
			}
		}
		if(e instanceof TrueLiteral){
			return true;
		}
		if(e instanceof FalseLiteral){
			return true;
		}
		return false;
	}
	
	private static boolean validBop(BinaryOp bo){
		return (validVar(bo.getTheLHS()) && validVar(bo.getTheRHS()));
	}
	
	private static boolean validEquiv(BinaryOp bo){
		if(bo.getTheBinaryOperator()!=BinaryOperator.EQUIV){
			return false;
		}
		if(!validVar(bo.getTheLHS())){
			return false;
		}
		return validBop((BinaryOp)bo.getTheRHS());
	}
	
	private static boolean sanityCheck(List<Expression> l){
		for(Expression e : l){
			System.out.println(PrintVisitor.expressionToString(e));
			if(e instanceof BinaryOp){
				if(!validEquiv((BinaryOp)e)){
					return false;
				}
			}
			else if(!(e instanceof Variable)){
				return false;
			}
		}
		return true;
	}
	
	private static List<Expression> pairList (List<Expression> l){
		List<Expression> result = new ArrayList<Expression>();
		int i;
		for(i=0; i<l.size()/2; i++){
			result.add(EF.createAnd(l.get(2*i), l.get((2*i)+1)));
		}
		if(l.size()%2==1){
			result.add(l.get(l.size()-1));
		}
		return result;
	}
	
	private static Expression andList(List<Expression> l){
		while(l.size()>1){
			l=pairList(l);
		}
		return l.get(0);
	}
	
	public static Expression convert(Expression e){
		ConverterContext context = new ConverterContext();
		context.visitResult = null;
		Converter con = new Converter(context);
		con.visit(e);
		context.equivs.add(context.visitResult);
//		if(!sanityCheck(context.equivs)){
//			throw new Error ("Problem!");
//		}
//		Expression finalresult = andList(context.equivs);
//		if(CycleChecker.checkForCycles(finalresult)){
//			throw new Error ("Cycle");
//		}
		return andList(context.equivs);
	}
	
	public Converter(ConverterContext g) {
		super(g);
	}
	
	@Override
	protected void visitUnaryOp(UnaryOp o) {
		visit(o.getTheExpression());
		o.setTheExpression(g.visitResult);
		g.visitResult = o;
		return;
	}
	
	@Override
	protected void visitVariable(Variable o) {
		this.g.visitResult = o;
		return;
	}
	
	//assumes only and and or
	protected void visitBinaryOp(BinaryOp o) {
		
	/*	if(g.exprCache.containsKey(o)){
			g.visitResult=g.exprCache.get(o);
			return;
		}	
*/		
		boolean topLevel = g.topLevel;
		g.topLevel=false;
		visit(o.getTheLHS());
		o.setTheLHS(g.visitResult);
		
		visit(o.getTheRHS());
		o.setTheRHS(g.visitResult);
		
		if(o.getTheRHS() instanceof BinaryOp)
			throw new Error("RHS Cycle");
		if(o.getTheLHS() instanceof BinaryOp)
			throw new Error("LHS Cycle");
		// {new va  <=> {this binary op}
		BinaryOp equiv = new BinaryOp();
		equiv.setTheBinaryOperator(BinaryOperator.EQUIV);
		Variable newT = new Variable();
		newT.setTheName("_t" + this.g.getFreshNumber());
		this.g.setFreshNumber(this.g.getFreshNumber()+1);
		equiv.setTheLHS(newT);
		equiv.setTheRHS(o);

		g.exprCache.put(o, newT);		
		
		//append to end of g.result
		g.equivs.add(equiv);
		
		g.visitResult = newT;
	}
	
	@Override
	protected void visitFalseLiteral(FalseLiteral o) {
		this.g.visitResult = o;
		return;
	}
	
	@Override
	protected void visitTrueLiteral(TrueLiteral o) {
		this.g.visitResult = o;
		return;
	}
	
}
