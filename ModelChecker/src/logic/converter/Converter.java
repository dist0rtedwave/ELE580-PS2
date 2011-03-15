package logic.converter;

/* will take an expression and create an expression with equivalences that the tseitin2cnf will handle*/

import logic.model.*;

public class Converter extends Visitor<ConverterContext> {

	public static Expression convert(Expression e){
		ConverterContext context = new ConverterContext();
		context.setTheExpression((BinaryOp) e);
		Converter con = new Converter(context);
		con.visit(e);
		return context.getTheExpression();
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
		//boolean weSetMakingEquivalences = false;
		if(!this.g.isMakingEquivalences()){
			if(o.getTheBinaryOperator().compareTo(BinaryOperator.AND)==0)
			{
				if(this.g.isOrSeen())
				{
					this.g.setMakingEquivalences(true);
					//weSetMakingEquivalences=true;
				}
			}
			else{
				this.g.setOrSeen(true);
			}
		}
		visit(o.getTheLHS());
		if(this.g.isMakingEquivalences()){
			if(!g.isLiteral()){
				Variable v = new Variable();
				v.setTheName("_t" + this.g.getFreshNumber());
				this.g.setFreshNumber(this.g.getFreshNumber()+1);
				o.setTheLHS(v);
			}
			else{
				g.setLiteral(false);
			}
		}
		
		visit(o.getTheRHS());
		if(this.g.isMakingEquivalences()){
			if(!g.isLiteral()){
				Variable v = new Variable();
				v.setTheName("_t" + this.g.getFreshNumber());
				this.g.setFreshNumber(this.g.getFreshNumber()+1);
				o.setTheRHS(v);
			}
			else{
				g.setLiteral(false);
			}
			
			
			BinaryOp equiv = new BinaryOp();
			equiv.setTheBinaryOperator(BinaryOperator.EQUIV);
			Variable newT = new Variable();
			newT.setTheName("_t" + this.g.getFreshNumber());
			equiv.setTheLHS(newT);
			equiv.setTheRHS(o);
			
			BinaryOp newExpr = new BinaryOp();
			newExpr.setTheLHS(g.getTheExpression());
			newExpr.setTheRHS(equiv);
			newExpr.setTheBinaryOperator(BinaryOperator.AND);
			
			g.setTheExpression(newExpr);
		}
		//if(weSetMakingEquivalences){ this.g.setMakingEquivalences(false);}
	}
	


}
