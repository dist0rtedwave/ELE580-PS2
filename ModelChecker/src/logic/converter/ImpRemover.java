package logic.converter;

import logic.model.BinaryOp;
import logic.model.BinaryOperator;
import logic.model.Expression;
import logic.model.UnaryOp;
import logic.model.UnaryOperator;
import logic.model.Variable;
import logic.model.Visitor;

public class ImpRemover extends Visitor<NotContext> {

	public static Expression removeImps(Expression e){
		NotContext context = new NotContext();
		ImpRemover dist = new ImpRemover(context);
		dist.visit(e);
		return context.getChild();
	}
	
	public ImpRemover(NotContext g) {
		super(g);
	}
	
	@Override
	protected void visitUnaryOp(UnaryOp o) {
		visit(o.getTheExpression());
		this.g.setChild(o);
	}
	
	@Override
	protected void visitVariable(Variable o) {
		this.g.setChild(o);
		return;
	}
	
	@Override
	protected void visitBinaryOp(BinaryOp o) {
		if(o.getTheBinaryOperator().compareTo(BinaryOperator.IMP) == 0)  // is IMP
		{
			UnaryOp notLHS = new UnaryOp();
			notLHS.setTheOperator(UnaryOperator.NOT);
			notLHS.setTheExpression(o.getTheLHS());
			o.setTheLHS(notLHS);
			o.setTheBinaryOperator(BinaryOperator.OR);
			o.setTheRHS(o.getTheRHS());
			
		}
		else if(o.getTheBinaryOperator().compareTo(BinaryOperator.EQUIV) == 0)  // is EQUIV
		{
			UnaryOp notLHS = new UnaryOp();
			notLHS.setTheOperator(UnaryOperator.NOT);
			notLHS.setTheExpression(o.getTheLHS());
			
			
			UnaryOp notRHS = new UnaryOp();
			notRHS.setTheOperator(UnaryOperator.NOT);
			notRHS.setTheExpression(o.getTheRHS());
		
			BinaryOp leftTree = new BinaryOp();
			leftTree.setTheBinaryOperator(BinaryOperator.OR);
			leftTree.setTheLHS(o.getTheLHS());
			leftTree.setTheRHS(notRHS);
			
			BinaryOp rightTree = new BinaryOp();
			rightTree.setTheBinaryOperator(BinaryOperator.OR);
			rightTree.setTheLHS(notLHS);
			rightTree.setTheRHS(o.getTheRHS());
			
			o.setTheBinaryOperator(BinaryOperator.AND);
			o.setTheLHS(leftTree);
			o.setTheRHS(rightTree);
			
		}
		visit(o.getTheLHS());
		o.setTheLHS(this.g.getChild());
		visit(o.getTheRHS());
		o.setTheRHS(this.g.getChild());
		
		this.g.setChild(o);
	}

}
