package logic.model;

public class Tseitin2CNFDecoder extends Visitor<Expression>{

	public Tseitin2CNFDecoder(Expression g) {
		super(g);
		// TODO Auto-generated constructor stub
	}
	public void visit(Expression o){
		switch (o.getDescriptor()){
		case Variable.DESCRIPTOR:
			visitVariable((Variable)o);
			break;
		case BinaryOp.DESCRIPTOR:
			visitBinaryOp((BinaryOp)o);
			break;
		case UnaryOp.DESCRIPTOR:
			visitUnaryOp((UnaryOp)o);
			break;
		}
	}

	protected void visitExpression(Expression o){
		assert o!=null;
	}
	
	protected void visitVariable(Variable o) {
		assert o != null;
		visitExpression(o);
	}
	
	Expression NegationTseitin(Expression LHS, Expression RHS)
	{
		return RHS;
		
	}
	
	protected void visitBinaryOp(BinaryOp o) {
		assert o != null;
		if(o.getTheBinaryOperator().compareTo(BinaryOperator.EQUIV) == 0)
		{
			
		}
		if(o.theLHS!=null){
			visit(o.theLHS);
		}
		if(o.getTheRHS()!=null){
			visit(o.theRHS);
		}
		visitExpression(o);
	}
	
	protected void visitUnaryOp(UnaryOp o){
		assert o!=null;
		if(o.theExpression!=null){
			visit(o.theExpression);
		}
		visitExpression(o);
	}
}
