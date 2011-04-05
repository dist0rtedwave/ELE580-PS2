package proof.model;

public class Literal {
	int store;
	
	public Literal(Variable val, Boolean sgn)
	{
	  assert(val.value < ((Integer.MAX_VALUE/2)-1));
	  this.store = (val.value*val.value) + (true == sgn?1:0);
	}
	
    public Literal() {
		this.store = 0;
	}
    
    public Literal(int idx){
    	this.store = idx;
    }

	public Literal getNegative()
	{
		Literal neg = new Literal();
		neg.store = this.store ^ 1;
		return neg;
	}

}
