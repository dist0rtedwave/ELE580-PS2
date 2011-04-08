package logic.converter;

import logic.model.BinaryOp;
import logic.model.Expression;
import logic.model.FalseLiteral;
import logic.model.UnaryOp;
import logic.model.Visitor;

public class DuplicateNotRemover extends Visitor<NotRemoverContext> {

	public DuplicateNotRemover(NotRemoverContext g) {
		super(g);
	}
	
	private Expression getTwoNotsChild(Expression e){
		if(e instanceof UnaryOp){
			if(((UnaryOp) e).getTheExpression() instanceof UnaryOp){
				return ((UnaryOp)e).getTheExpression();
			}
		}
		return null;
	}
	
	@Override
	protected void visitBinaryOp(BinaryOp o) {
		Expression child = getTwoNotsChild(o.getTheLHS());
		while(child!=null){
			o.setTheLHS(child);
			child=getTwoNotsChild(o.getTheLHS());
		}
		visit(o.getTheLHS());
		child = getTwoNotsChild(o.getTheRHS());
		while(child!=null){
			o.setTheRHS(child);
			child=getTwoNotsChild(o.getTheRHS());
		}
		visit(o.getTheRHS());
	}

}
