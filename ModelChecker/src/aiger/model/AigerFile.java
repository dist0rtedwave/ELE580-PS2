package aiger.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AigerFile {
	Set<Expression> theOutputs = new HashSet<Expression>();
	Map<String, Expression> theSymbols = new HashMap<String, Expression>();
	public Set<Expression> getTheOutputs() {
		return theOutputs;
	}
	public void setTheOutputs(Set<Expression> theOutputs) {
		this.theOutputs = theOutputs;
	}
	public Map<String, Expression> getTheSymbols() {
		return theSymbols;
	}
	public void setTheSymbols(Map<String, Expression> theSymbols) {
		this.theSymbols = theSymbols;
	}
	public AigerFile(Set<Expression> theOutputs,
			Map<String, Expression> theSymbols) {
		super();
		this.theOutputs = theOutputs;
		this.theSymbols = theSymbols;
	}
	
		
}
