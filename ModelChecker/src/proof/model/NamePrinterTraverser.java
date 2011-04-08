package proof.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class NamePrinterTraverser extends IdentifierPrinterTraverser {
	HashMap<Integer, String> lookupMap;
	
	public void setNameMap(HashMap<String, Integer> nameMap)
	{
		this.lookupMap = new HashMap<Integer, String>();
		for(Entry<String, Integer> entry: nameMap.entrySet())
		{
			//System.out.print(entry.getKey());
			//System.out.print(entry.getValue());
			lookupMap.put(entry.getValue()-1, entry.getKey());
		}
		
	}
	
	String getName(int var_no)
	{
		return lookupMap.get(var_no); 
	}
	
	String getLiteralName(Literal lit)
	{
		if(lit.isNegative())
		{
			return "-"+getName(lit.getVariableNo());
		}
		return getName(lit.getVariableNo());
	}
	
	@Override
	void printLiterals(ArrayList<Literal> literals)
	{
		if (literals == null)
		{
			System.out.print("[null]");
			return;
		}
		if(literals.size()==0)
		{
			System.out.print("[empty]");
		}
		Iterator<Literal> it_literal;
		it_literal = literals.iterator();
		while(it_literal.hasNext()) {System.out.print(" "+getLiteralName(it_literal.next()));}
	}
	
	@Override
	void printVariable(Variable var)
	{
		System.out.print(getName(var.getVariableNo()));
	}

}
