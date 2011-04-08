package logic.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import logic.model.Expression;

public class ConverterContext {
	protected Expression visitResult;
    private int freshNumber =0;
    boolean topLevel=true;
    protected List<Expression> equivs =  new ArrayList<Expression>();
    
    protected HashMap<Expression, Expression> exprCache = new HashMap<Expression, Expression>();
   

	public void setFreshNumber(int freshNumber) {
		this.freshNumber = freshNumber;
	}

	public int getFreshNumber() {
		return freshNumber;
	}


	
}
