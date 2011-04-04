package aiger.model;

public class AndGate extends Expression {
	private Expression theLeftInput;
	private Expression theRightInput;
	private int theName;
	
	public AndGate(Expression theLeftInput, Expression theRightInput,
			int theName) {
		super();
		this.theLeftInput = theLeftInput;
		this.theRightInput = theRightInput;
		this.theName = theName;
	}
	  public final int getDescriptor() {
		  return DESCRIPTOR;
	  }
	  
	public final static int DESCRIPTOR=1;
	
	public AndGate(int theName){
		this.theName=theName;
	}
	
	public Expression getTheLeftInput() {
		return theLeftInput;
	}
	public void setTheLeftInput(Expression theLeftInput) {
		this.theLeftInput = theLeftInput;
	}
	public Expression getTheRightInput() {
		return theRightInput;
	}
	public void setTheRightInput(Expression theRightInput) {
		this.theRightInput = theRightInput;
	}
	public int getTheName() {
		return theName;
	}
	public void setTheName(int theName) {
		this.theName = theName;
	}
	
}
