package logic.converter;

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
}
