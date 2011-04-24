package logic.printer;

import logic.model.*;

import java.util.HashMap;

public class DimacsPrinter extends ClauseVisitor<StringBuilder> {
	protected HashMap<String, Integer> nameMap;
	protected int nameCounter;
	protected int andCounter;
	

	public DimacsPrinter(StringBuilder g) {
		super(g);
		this.nameMap = new HashMap<String, Integer>();
		this.nameCounter = 1;
		this.andCounter = 0;
	}
	
	@Override
    public void addNegativeLiteral(Variable v)
	{
    	g.append("-");
    	this.addPositiveLiteral(v);
	}
    
	@Override
    public void addPositiveLiteral(Variable o)
	{
		if (!this.nameMap.containsKey(o.getTheName()))
		{
			this.nameMap.put(o.getTheName(), new Integer(this.nameCounter));
			this.nameCounter += 1;
		}
		g.append(this.nameMap.get(o.getTheName())+" ");
	}
	
	@Override
    public void endClause()
	{
		this.andCounter += 1;
		g.append("0 \n");
	}
	
	public String expressionToString(Expression e){		
		this.visitFull(e);
		
		return "c "+PrintVisitor.expressionToString(e)+"\n"+"p cnf "+ (this.nameCounter-1) +" "+ (this.andCounter)+"\n"+g.toString();
	}
	
	public HashMap<String, Integer> getNameMap()
	{
		return this.nameMap;
	}
}
