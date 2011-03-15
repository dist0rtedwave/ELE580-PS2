package logic.converter;

/* will take an expression and create an expression with equivalences that the tseitin2cnf will handle*/

import logic.model.*;

public class ImpRemover extends Visitor<BinaryOp> {

	public static Expression convert(BinaryOp e){
		BinaryOp removed = new BinaryOp();
		removed.setTheLHS(e.getTheLHS());
		removed.setTheRHS(e.getTheRHS());
		removed.setTheBinaryOperator(e.getTheBinaryOperator());
		ImpRemover remover = new ImpRemover(removed);
		remover.visit(e);
		return removed;
	}
	
	public ImpRemover(BinaryOp g) {
		super(g);
	}
	
	@Override
	protected void visitUnaryOp(UnaryOp o) {
		visit(o.getTheExpression());
	}
	
	@Override
	protected void visitVariable(Variable o) {
		return;
	}
	
	//assumes only and and or
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
		visit(o.getTheRHS());
	}
	


}
