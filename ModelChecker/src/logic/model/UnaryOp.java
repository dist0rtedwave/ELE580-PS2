package logic.model;

public class UnaryOp extends Expression {
	
	protected Expression theExpression;
	protected UnaryOperator theOperator;
	
	public Expression getTheExpression() {
		return theExpression;
	}

	public UnaryOperator getTheOperator() {
		return theOperator;
	}

	public void setTheOperator(UnaryOperator theOperator) {
		this.theOperator = theOperator;
	}

	public void setTheExpression(Expression theExpression) {
		this.theExpression = theExpression;
	}
	
	  public final int getDescriptor() {
		    return DESCRIPTOR;
		  }
	public static final int DESCRIPTOR=3;
}
