package logic.model;

public class BinaryOp extends Expression {
	
	  public final int getDescriptor() {
		  return DESCRIPTOR;
	  }
	  
	public final static int DESCRIPTOR=2;
	protected Expression theLHS;
	protected Expression theRHS;
	protected BinaryOperator theBinaryOperator;
	
	public Expression getTheLHS() {
		return theLHS;
	}
	public void setTheLHS(Expression theLHS) {
		this.theLHS = theLHS;
	}
	public Expression getTheRHS() {
		return theRHS;
	}
	public void setTheRHS(Expression theRHS) {
		this.theRHS = theRHS;
	}
	public BinaryOperator getTheBinaryOperator() {
		return theBinaryOperator;
	}
	public void setTheBinaryOperator(BinaryOperator theBinaryOperator) {
		this.theBinaryOperator = theBinaryOperator;
	}
}
