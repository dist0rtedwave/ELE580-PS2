package proof.model;

public class Variable {
	int value = 0;
	
	public Variable(int x)
	{
		this.value = x;
	}
	
	@Override
	public String toString()
	{
		return ""+this.value;
	}
	
	public int getVariableNo()
	{
		return this.value;
	}

}
