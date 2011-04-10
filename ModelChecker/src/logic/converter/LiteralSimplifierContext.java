package logic.converter;

import java.util.HashMap;

import logic.model.Expression;



public class LiteralSimplifierContext {

	
	protected HashMap<Expression, literalInfo> exprCache = new HashMap<Expression, literalInfo>();
	protected Expression result;
	protected boolean isLiteral;
	protected boolean theLiteral;
}
