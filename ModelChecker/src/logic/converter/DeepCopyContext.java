package logic.converter;

import java.util.HashMap;

import logic.model.Expression;

public class DeepCopyContext {
	protected Expression visitResult;
	protected HashMap<Expression, Expression> exprCache = new HashMap<Expression, Expression>();   
	
				

}
