package logic.converter;

import logic.model.BinaryOp;
import logic.model.BinaryOperator;
import logic.model.EF;
import logic.model.Expression;
import logic.model.FalseLiteral;
import logic.model.TrueLiteral;
import logic.model.UnaryOp;
import logic.model.UnaryOperator;
import logic.model.Variable;
import logic.model.Visitor;

public class ImpRemover extends Visitor<ImpContext> {

	public static Expression removeImps(Expression e){
		ImpContext context = new ImpContext();
		ImpRemover dist = new ImpRemover(context);
		dist.visit(e);
		return context.theResult;
	}
	
	public ImpRemover(ImpContext g) {
		super(g);
	}
	
	@Override
	protected void visitUnaryOp(UnaryOp o) {
		if(g.exprCache.containsKey(o)){
			g.theResult = g.exprCache.get(o);
			return;
		}
		visit(o.getTheExpression());
		o.setTheExpression(g.theResult);
		g.theResult = o;
		g.exprCache.put(o, g.theResult);
		return;
	}
	
	@Override
	protected void visitVariable(Variable o) {
		g.theResult = o;
		return;
	}
	
	@Override
	protected void visitFalseLiteral(FalseLiteral o) {
		g.theResult = o;
		return;
	}
	
	@Override
	protected void visitTrueLiteral(TrueLiteral o) {
		g.theResult = o;
		return;
	}
	
	@Override
	protected void visitBinaryOp(BinaryOp o) {
		if(g.exprCache.containsKey(o)){
			g.theResult = g.exprCache.get(o);
			return;
		}
		Expression newExp = null;
		visit(o.getTheLHS());
		o.setTheLHS(g.theResult);
		visit(o.getTheRHS());
		o.setTheRHS(g.theResult);
		if(o.getTheBinaryOperator().compareTo(BinaryOperator.IMP) == 0)  // is IMP
		{
			newExp = EF.createOr(EF.createNot(o.getTheLHS()), o.getTheRHS());	
		}
		else if(o.getTheBinaryOperator().compareTo(BinaryOperator.EQUIV) == 0)  // is EQUIV
		{
			newExp = EF.createOr(EF.createAnd(o.getTheLHS(), o.getTheRHS()), EF.createAnd(EF.createNot(o.getTheLHS()),EF.createNot(o.getTheRHS())));
		}
		else
		{
			newExp = o;
		}

		g.exprCache.put(o, newExp);
		g.theResult = newExp;
	}

}
