package aiger.model;

public class Not extends Expression{
	private Expression theExpression;

	public Expression getTheExpression() {
		return theExpression;
	}

	public void setTheExpression(Expression theExpression) {
		this.theExpression = theExpression;
	}

	public Not(Expression theExpression) {
		super();
		this.theExpression = theExpression;
	}
	
}
