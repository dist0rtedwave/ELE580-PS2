package util;

public class Pair<A,B> {
	private A first;
	private B second;

	public Pair(A first, B second) {
		super();
		this.first = first;
		this.second = second;
	}
	

	public void setFirst(A first) {
		this.first = first;
	}


	public void setSecond(B second) {
		this.second = second;
	}

	public A first()
	{
		return this.first;
	}
	
	public B second()
	{
		return this.second;
	}

}
