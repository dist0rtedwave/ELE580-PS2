package proof.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import logic.printer.*;

public class NamePrinterTraverser extends IdentifierPrinterTraverser {
	NameMap nameMap;
	
	public void setNameMap(NameMap nameMap)
	{
		this.nameMap = nameMap;		
	}
	
	String getName(int var_no)
	{
		return this.nameMap.getVarName(var_no); 
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
	void printLiterals(Set<Literal> literals)
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
