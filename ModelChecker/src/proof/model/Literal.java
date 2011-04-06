package proof.model;

import java.util.ArrayList;

public class Literal {
	final int store;
	
	public Literal(Variable val, Boolean sgn)
	{
	  assert(val.value < ((Integer.MAX_VALUE/2)-1));
	  this.store = (val.value+val.value) + (true == sgn?1:0);
	}
	
    public Literal(int idx){
    	this.store = idx;
    }

	public Literal getNegative()
	{
		Literal neg = new Literal((this.store ^1));
		return neg;
	}
	
	public Variable getVariable()
	{
		return new Variable(getVariableNo());
	}
	
	public int getVariableNo()
	{
		return (this.store>>1);
	}
	
	public Boolean isNegative()
	{
		return ((this.store & 1) == 1);
	}
	public String toString()
	{
		Variable v = this.getVariable();		
		if(this.isNegative())
		{
			return "-"+getVariableNo();
		}
		return ""+getVariableNo();	
	}
	
    @Override public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Literal) {
            Literal that = (Literal) other;
            result = (this.store == that.store);
        }
        return result;
    }

    @Override public int hashCode() {
        return (this.store);
    }

	
}
