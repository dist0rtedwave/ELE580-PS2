package aiger.model;

public class Latch extends Expression {
	private Variable theCurrentState;
	private Expression theNextState;
	
	public Latch(String currentState){
		this.theCurrentState = new Variable(currentState);
	}
	
	public Variable getTheCurrentState() {
		return theCurrentState;
	}
	public void setTheCurrentState(Variable theCurrentState) {
		this.theCurrentState = theCurrentState;
	}
	
	public Expression getTheNextState() {
		return theNextState;
	}
	
	public void setTheNextState(Expression theNextState) {
		this.theNextState = theNextState;
	}
	
	
}
