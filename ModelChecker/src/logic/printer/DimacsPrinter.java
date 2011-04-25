package logic.printer;

import logic.model.*;

import java.util.HashMap;

public class DimacsPrinter extends ClauseVisitor<StringBuilder> {
	//nameMap is 0 indexed
	protected NameMap nameMap;
	protected int andCounter;
	

	public DimacsPrinter(StringBuilder g) {
		super(g);
		this.nameMap = new NameMap();
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
		int num = this.nameMap.getDimacsIndex(o.getTheName());
		g.append(num+" ");
	}
	
	@Override
    public void endClause()
	{
		this.andCounter += 1;
		g.append("0 \n");
	}
	
	public String expressionToString(Expression e){		
		this.visitFull(e);
		
		return "c "+PrintVisitor.expressionToString(e)+"\n"+"p cnf "+ (this.nameMap.getSize()) +" "+ (this.andCounter)+"\n"+g.toString();
	}
	
	public NameMap getNameMap()
	{
		return this.nameMap;
	}
}
