package logic.converter;

import logic.model.BinaryOp;
import logic.model.Expression;

public class ConverterContext {
	private boolean orSeen=false;
	private Expression theExpression;
    private int freshNumber =0;
    private boolean makingEquivalences=false;
    private boolean isLiteral=false;
    
	public boolean isMakingEquivalences() {
		return makingEquivalences;
	}

	public void setMakingEquivalences(boolean makingEquivalences) {
		this.makingEquivalences = makingEquivalences;
	}

	public void setOrSeen(boolean orSeen) {
		this.orSeen = orSeen;
	}

	public boolean isOrSeen() {
		return orSeen;
	}

	public void setTheExpression(Expression e) {
		this.theExpression = e;
	}

	public Expression getTheExpression() {
		return theExpression;
	}

	public void setFreshNumber(int freshNumber) {
		this.freshNumber = freshNumber;
	}

	public int getFreshNumber() {
		return freshNumber;
	}

	public void setLiteral(boolean isLiteral) {
		this.isLiteral = isLiteral;
	}

	public boolean isLiteral() {
		return isLiteral;
	}
	
}
