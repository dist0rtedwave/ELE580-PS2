package aiger.bmc;

import java.util.HashMap;
import java.util.Map;

import aiger.model.Expression;

public class ClonerContext {
	protected int offset;
	private Map<Expression, Expression> theCopies = new HashMap<Expression, Expression>();
	private Expression result=null;
	public Map<Expression, Expression> getTheCopies() {
		return theCopies;
	}
	public void setTheCopies(Map<Expression, Expression> theCopies) {
		this.theCopies = theCopies;
	}
	public Expression getResult() {
		return result;
	}
	public void setResult(Expression result) {
		this.result = result;
	}

}
