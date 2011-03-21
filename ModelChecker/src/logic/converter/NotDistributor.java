package logic.converter;

import logic.model.BinaryOp;
import logic.model.BinaryOperator;
import logic.model.Expression;
import logic.model.UnaryOp;
import logic.model.UnaryOperator;
import logic.model.Variable;
import logic.model.Visitor;

public class NotDistributor extends Visitor<NotContext> {

	public static Expression distributeNots(Expression e){
		NotContext context = new NotContext();
		NotDistributor dist = new NotDistributor(context);
		dist.visit(e);
		return context.getChild();
	}
	
	public NotDistributor(NotContext g) {
		super(g);
	}
	
	@Override
	protected void visitUnaryOp(UnaryOp o) {
		if(o.getTheOperator()==UnaryOperator.NOT){
			this.g.setNegating(!this.g.isNegating());
			visit(o.getTheExpression());
			this.g.setNegating(!this.g.isNegating());
		}	
	}
	
	@Override
	protected void visitVariable(Variable o) {
		if(this.g.isNegating()){
			UnaryOp op = new UnaryOp();
			op.setTheOperator(UnaryOperator.NOT);
			op.setTheExpression(o);
			this.g.setChild(op);
		}
		else{
			this.g.setChild(o);
		}
	}
	
	@Override
	protected void visitBinaryOp(BinaryOp o) {
		if(this.g.isNegating()){
			if(o.getTheBinaryOperator()==BinaryOperator.AND){
				o.setTheBinaryOperator(BinaryOperator.OR);
			}
			else{
				o.setTheBinaryOperator(BinaryOperator.AND);
			}
		}
		visit(o.getTheLHS());
		o.setTheLHS(this.g.getChild());
		visit(o.getTheRHS());
		o.setTheRHS(this.g.getChild());
		
		this.g.setChild(o);
	}

}
