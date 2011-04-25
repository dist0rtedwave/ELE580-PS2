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
	
    @Override public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Variable) {
            Variable that = (Variable) other;
            result = (this.value == that.value);
        }
        return result;
    }

    @Override public int hashCode() {
        return (this.value);
    }

}
