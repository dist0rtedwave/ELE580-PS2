package aiger.model;

public class Variable extends Expression {
	private int theVariableName;
	
	public Variable(int name){
		this.theVariableName=name;
	}

	public int getTheVariableName() {
		return theVariableName;
	}

	public void setTheVariableName(int theVariableName) {
		this.theVariableName = theVariableName;
	}
	
	  public final int getDescriptor() {
		  return DESCRIPTOR;
	  }
	  
	public final static int DESCRIPTOR=7;
}
