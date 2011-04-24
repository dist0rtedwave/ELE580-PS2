package logic.model;

abstract public class ClauseVisitor<G> extends Visitor<G>{
	
	public ClauseVisitor(G g) {
		super(g);
	}
	
	public void visitFull(Expression e)
	{
		this.visit(e);
	    endClause();		
	}
	
	protected void visitVariable(Variable o) {
		addPositiveLiteral(o);
	}
	
	@Override
	protected void visitFalseLiteral(FalseLiteral o) {
		assert(false); //should never get an expression with literals left over (handled by LiteralSimplifier)
	}
	
	@Override
	protected void visitTrueLiteral(TrueLiteral o) {
		assert(false); //should never get an expression with literals left over (handled by LiteralSimplifier) 
	}
	
	protected void visitBinaryOp(BinaryOp o) {
		visit(o.getTheLHS());
		if(o.getTheBinaryOperator() == BinaryOperator.AND)
		{
			endClause();
		}
		else
		{
			assert(o.getTheBinaryOperator() == BinaryOperator.OR);
		}
		visit(o.getTheRHS());
	}
	
	protected void visitUnaryOp(UnaryOp o){
		assert o!=null;
		assert o.getTheOperator() == UnaryOperator.NOT;
		Expression e = o.getTheExpression();
		assert e.getDescriptor() == Variable.DESCRIPTOR;
		addNegativeLiteral((Variable)e);
	}
	
	abstract public void addNegativeLiteral(Variable v);	
	abstract public void addPositiveLiteral(Variable v);	
	abstract public void endClause();

}
