package aiger.bmc;

import java.util.HashMap;
import java.util.Map;

import logic.model.Expression;

public class CNFTranslatorContext {
	
	protected Map<aiger.model.Expression, Expression> exMap = new HashMap<aiger.model.Expression, Expression>();
	public Expression result; 

}
