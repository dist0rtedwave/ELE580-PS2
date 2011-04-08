package aiger.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AigerFile {
	Set<Expression> theOutputs = new HashSet<Expression>();
	Map<Integer, Expression> theSymbols = new HashMap<Integer, Expression>();
	Set<Latch> theLatches = new HashSet<Latch>();
	int theMaxVariable;
	
	public int getTheMaxVariable() {
		return theMaxVariable;
	}
	public void setTheMaxVariable(int theMaxVariable) {
		this.theMaxVariable = theMaxVariable;
	}
	public Set<Latch> getTheLatches() {
		return theLatches;
	}
	public void setTheLatches(Set<Latch> theLatches) {
		this.theLatches = theLatches;
	}
	public Set<Expression> getTheOutputs() {
		return theOutputs;
	}
	public void setTheOutputs(Set<Expression> theOutputs) {
		this.theOutputs = theOutputs;
	}
	public Map<Integer, Expression> getTheSymbols() {
		return theSymbols;
	}
	public void setTheSymbols(Map<Integer, Expression> theSymbols) {
		this.theSymbols = theSymbols;
	}
	public AigerFile(Set<Expression> theOutputs,
			Map<Integer, Expression> theSymbols) {
		super();
		this.theOutputs = theOutputs;
		this.theSymbols = theSymbols;
	}
	
	public AigerFile(){
		
	}
	
		
}
