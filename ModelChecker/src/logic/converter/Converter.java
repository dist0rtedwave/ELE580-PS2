package logic.converter;

/* will take an expression and create an expression with equivalences that the tseitin2cnf will handle*/

import logic.model.*;

public class Converter extends Visitor<ConverterContext> {

	public static Expression convert(Expression e){
		ConverterContext context = new ConverterContext();
		context.globalResult = e;
		context.visitResult = null;
		Converter con = new Converter(context);
		con.visit(e);
		return context.globalResult;
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
		BinaryOp newExpr = new BinaryOp();
		newExpr.setTheLHS(g.globalResult);
		newExpr.setTheRHS(equiv);
		newExpr.setTheBinaryOperator(BinaryOperator.AND);
		
		g.visitResult = newT;
		g.globalResult = newExpr;
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
