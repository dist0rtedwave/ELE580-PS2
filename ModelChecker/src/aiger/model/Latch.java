package aiger.model;

public class Latch extends Expression {
	private Expression theCurrentState;
	private Expression theNextState;
	private Expression theInterimState;
	
	public Expression getTheInterimState() {
		return theInterimState;
	}

	public void setTheInterimState(Expression theInterimState) {
		this.theInterimState = theInterimState;
	}

	public void setTheCurrentState(Expression theCurrentState) {
		this.theCurrentState = theCurrentState;
	}

	public Latch(int currentState){
		this.theCurrentState = new Variable(currentState);
	}
	
	public Latch(Expression expr){
		this.theCurrentState = expr;
	}
	
	public Expression getTheCurrentState() {
		return theCurrentState;
	}
	
	
	public Expression getTheNextState() {
		return theNextState;
	}
	
	public void setTheNextState(Expression theNextState) {
		this.theNextState = theNextState;
	}
	
	  public final int getDescriptor() {
		  return DESCRIPTOR;
	  }
	  
	public final static int DESCRIPTOR=3;
	
}
