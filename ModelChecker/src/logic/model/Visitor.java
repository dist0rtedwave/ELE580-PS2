package logic.model;

public class Visitor<G> {

	protected G g;
	  
	public Visitor(G g) {
	  assert g != null;
	  this.g = g;
	}
	
	public void visit(Expression o){
		if(o == null)
			return;
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
	
	protected void visitBinaryOp(BinaryOp o) {
		assert o != null;
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
