package logic.converter;

import java.util.HashMap;
import java.util.Map;

import logic.model.Expression;

public class ConverterContext {
	protected Expression globalResult;
	protected Expression visitResult;
    private int freshNumber =0;
    private boolean isLiteral=false;
    
    protected HashMap<Expression, Expression> exprCache = new HashMap<Expression, Expression>();
   

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
