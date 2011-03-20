package logic.decoder;

import logic.model.Expression;

public class TseitinDecoderContext {
	private Expression child=null;
	public Expression getChild() {
		return child;
	}
	public void setChild(Expression child) {
		this.child = child;
	}

}
