package logic.converter;

import java.util.HashMap;
import java.util.Map;

import logic.model.Expression;

public class NotContext {
	private Expression child=null;
	public Expression getChild() {
		return child;
	}
	public void setChild(Expression child) {
		this.child = child;
	}
	public boolean isNegating() {
		return negating;
	}
	public void setNegating(boolean negating) {
		this.negating = negating;
	}
	private boolean negating=false; 

	protected Map<Expression, Expression> nonNegatedMap=new HashMap<Expression, Expression>();
	protected Map<Expression, Expression> negatedMap = new HashMap<Expression, Expression>();

}
