package aiger.model;

public class Variable extends Expression {
	private String theVariableName;
	
	public Variable(String name){
		this.theVariableName=name;
	}

	public String getTheVariableName() {
		return theVariableName;
	}

	public void setTheVariableName(String theVariableName) {
		this.theVariableName = theVariableName;
	}
}
