package logic.model;

public class Visitor<G> {

	protected G g;
	  
	public Visitor(G g) {
	  assert g != null;
	  this.g = g;
	}
	
	public void visit(Expression o, Boolean PrintExpr){
		switch (o.getDescriptor()){
		case Variable.DESCRIPTOR:
			visitVariable((Variable)o, PrintExpr);
			break;
		case BinaryOp.DESCRIPTOR:
			visitBinaryOp((BinaryOp)o, PrintExpr);
			break;
		case UnaryOp.DESCRIPTOR:
			visitUnaryOp((UnaryOp)o, PrintExpr);
			break;
		}
	}

	protected void visitExpression(Expression o, Boolean PrintExpr){
		assert o!=null;
	}
	
	protected void visitVariable(Variable o, Boolean PrintExpr) {
		assert o != null;
		if(PrintExpr)
			System.out.print(o.getTheName());
		visitExpression(o, PrintExpr);
	}
	
	protected void visitBinaryOp(BinaryOp o, Boolean PrintExpr) {
		assert o != null;
		if(PrintExpr)
			System.out.print(" (");
		if(o.theLHS!=null){
			visit(o.theLHS, PrintExpr);
		}
		if(PrintExpr)
		{
			System.out.print(") ");
			System.out.print(o.getTheBinaryOperator());
			System.out.print(" (");
		}
		if(o.getTheRHS()!=null){
			visit(o.theRHS, PrintExpr);
		}
		if(PrintExpr)
			System.out.print(") ");
		visitExpression(o, PrintExpr);
	}
	
	protected void visitUnaryOp(UnaryOp o, Boolean PrintExpr){
		assert o!=null;
		if(PrintExpr)
			System.out.print(o.getTheOperator());
		if(o.theExpression!=null){
			visit(o.theExpression, PrintExpr);
		}
		visitExpression(o, PrintExpr);
	}

}
