package logic.model;

public class Variable extends Expression {
	
	public final int getDescriptor() {
		    return DESCRIPTOR;
	}
	public static final int DESCRIPTOR=1;
	
	protected String theName;

	public String getTheName() {
		return theName;
	}

	public void setTheName(String theName) {
		this.theName = theName;
	}
}
