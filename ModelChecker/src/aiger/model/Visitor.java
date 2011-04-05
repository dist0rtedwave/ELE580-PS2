package aiger.model;

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
		case AndGate.DESCRIPTOR:
			visitAndGate((AndGate)o);
			break;
		case Not.DESCRIPTOR:
			visitNot((Not)o);
			break;
		case Latch.DESCRIPTOR:
			visitLatch((Latch)o);
			break;
		case TrueLiteral.DESCRIPTOR:
			visitTrueLiteral((TrueLiteral)o);
			break;	
		case FalseLiteral.DESCRIPTOR:
			visitFalseLiteral((FalseLiteral)o);
			break;	
		}
	}

	private void visitTrueLiteral(TrueLiteral o) {
		assert o != null;
		visitExpression(o);
	}

	private void visitFalseLiteral(FalseLiteral o) {
		assert o != null;
		visitExpression(o);
	}

	protected void visitExpression(Expression o){
		assert o!=null;
	}
	
	protected void visitVariable(Variable o) {
		assert o != null;
		visitExpression(o);
	}
	
	protected void visitLatch(Latch o) {
		assert o != null;
		if(o.getTheCurrentState()!=null){
			visit(o.getTheCurrentState());
		}
		if(o.getTheNextState()!=null){
			visit(o.getTheNextState());
		}
		visitExpression(o);
	}
	
	protected void visitAndGate(AndGate o) {
		assert o != null;
		if(o.getTheLeftInput()!=null){
			visit(o.getTheLeftInput());
		}
		if(o.getTheRightInput()!=null){
			visit(o.getTheRightInput());
		}
		visitExpression(o);
	}
	
	protected void visitNot(Not o){
		assert o!=null;
		if(o.getTheExpression()!=null){
			visit(o.getTheExpression());
		}
		visitExpression(o);
	}

}
