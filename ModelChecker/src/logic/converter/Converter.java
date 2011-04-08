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
		this.g.setLiteral(true);
	}
	
	@Override
	protected void visitVariable(Variable o) {
		this.g.setLiteral(true);
	}
	
	//assumes only and and or
	protected void visitBinaryOp(BinaryOp o) {
		
		if(g.exprCache.containsKey(o)){
			g.visitResult=g.exprCache.get(o);
			return;
		}	
		
		visit(o.getTheLHS());
		
		if (!g.isLiteral()) {
			o.setTheLHS(g.visitResult);
		} else {
			g.setLiteral(false);
		}

		visit(o.getTheRHS());
		
		if (!g.isLiteral()) {
			o.setTheRHS(g.visitResult);
		} else {
			g.setLiteral(false);
		}


		if(g.exprCache.containsKey(o)){
			g.globalResult=g.exprCache.get(o);
			return;
		}
		
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
		this.g.setLiteral(true);
	}
	
	@Override
	protected void visitTrueLiteral(TrueLiteral o) {
		this.g.setLiteral(true);
	}
	
}
