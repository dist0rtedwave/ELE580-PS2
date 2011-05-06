package interpolant.checker;

import java.util.HashMap;

import logic.model.Expression;

public class TimeShifterContext {
	protected HashMap<Expression, Expression> exprCache = new HashMap<Expression, Expression>();
   
	protected int maxVariableIndex;

	public int getMaxVariableIndex() {
		return maxVariableIndex;
	}

	public void setMaxVariableIndex(int maxVariableIndex) {
		this.maxVariableIndex = maxVariableIndex;
	} 
}
