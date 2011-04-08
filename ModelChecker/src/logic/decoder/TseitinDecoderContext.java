package logic.decoder;

import java.util.HashMap;

import logic.model.Expression;

public class TseitinDecoderContext {
	
	protected HashMap<Expression, Expression> exprCache = new HashMap<Expression, Expression>();
	private Expression child=null;
	public Expression getChild() {
		return child;
	}
	public void setChild(Expression child) {
		this.child = child;
	}

}
