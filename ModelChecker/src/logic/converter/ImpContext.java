package logic.converter;

import java.util.HashMap;

import logic.model.Expression;

public class ImpContext {
	protected Expression theResult=null;
	protected HashMap<Expression, Expression> exprCache = new HashMap<Expression, Expression>();
	


}
