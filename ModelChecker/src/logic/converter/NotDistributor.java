package logic.converter;

import java.util.Map;

import logic.model.BinaryOp;
import logic.model.BinaryOperator;
import logic.model.Expression;
import logic.model.FalseLiteral;
import logic.model.TrueLiteral;
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
	
	private Map<Expression, Expression> selectMap(){
		if(g.isNegating()){
			return g.negatedMap;
		}
		return g.nonNegatedMap;
	}
	
	@Override
	protected void visitVariable(Variable o) {
		Map<Expression, Expression> map = selectMap();
		if(map.containsKey(o)){
			this.g.setChild(map.get(o));
			return;
		}
		if(this.g.isNegating()){
			UnaryOp op = new UnaryOp();
			op.setTheOperator(UnaryOperator.NOT);
			op.setTheExpression(o);
			this.g.setChild(op);
			map.put(o, op);
		}
		else{
			this.g.setChild(o);
			map.put(o, o);
		}
	}
	
	@Override
	protected void visitBinaryOp(BinaryOp o) {
		Map<Expression, Expression> map = selectMap();
		if(map.containsKey(o)){
			g.setChild(map.get(o));
			return;
		}
		BinaryOp bop=null;
		if(this.g.isNegating()){
			 bop = new BinaryOp();
			if(o.getTheBinaryOperator()==BinaryOperator.AND){
				bop.setTheBinaryOperator(BinaryOperator.OR);
			}
			else{
				bop.setTheBinaryOperator(BinaryOperator.AND);
			}
		}else{
			bop=o;
		}
		visit(o.getTheLHS());
		bop.setTheLHS(this.g.getChild());
		visit(o.getTheRHS());
		bop.setTheRHS(this.g.getChild());
		this.g.setChild(bop);
		map.put(o, bop);
	}
	
	@Override
	protected void visitFalseLiteral(FalseLiteral o) {
		Map<Expression, Expression> map = selectMap();
		if(map.containsKey(o)){
			g.setChild(map.get(o));
			return;
		}
		if(this.g.isNegating()){
			this.g.setChild(new TrueLiteral());
		}
		else{
			this.g.setChild(o);
		}
		map.put(o, g.getChild());
	}
	
	@Override
	protected void visitTrueLiteral(TrueLiteral o) {
		Map<Expression, Expression> map = selectMap();
		if(map.containsKey(o)){
			g.setChild(map.get(o));
			return;
		}
		if(this.g.isNegating()){
			this.g.setChild(new FalseLiteral());
		}
		else{
			this.g.setChild(o);
		}
		map.put(o, g.getChild());
	}

}
