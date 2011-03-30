package aiger.model;

public class AndGate extends Expression {
	private Expression theLeftInput;
	private Expression theRightInput;
	private String theName;
	
	public AndGate(Expression theLeftInput, Expression theRightInput,
			String theName) {
		super();
		this.theLeftInput = theLeftInput;
		this.theRightInput = theRightInput;
		this.theName = theName;
	}
	
	public AndGate(String theName){
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
	public String getTheName() {
		return theName;
	}
	public void setTheName(String theName) {
		this.theName = theName;
	}
	
}
