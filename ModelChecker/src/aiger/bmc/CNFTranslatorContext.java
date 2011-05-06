package aiger.bmc;

import java.util.HashMap;
import java.util.Map;

import logic.model.Expression;

public class CNFTranslatorContext {
	
	protected Map<aiger.model.Expression, Expression> exMap = new HashMap<aiger.model.Expression, Expression>();
	public Expression result; 
	protected boolean inLatch=false;
	protected Map<aiger.model.Expression, Expression> latchExMap = new HashMap<aiger.model.Expression, Expression>();
	protected int offset=0;
	protected int currentTime= 0;
	protected boolean latchesAsVariables = false;
	
}
