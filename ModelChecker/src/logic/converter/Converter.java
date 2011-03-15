package logic.converter;

/* will take an expression and create an expression with equivalences that the tseitin2cnf will handle*/

import logic.model.*;

public class Converter extends Visitor<Expression> {

	Boolean OrSeen = false;		//keeps track of whether or not an OR has been seen. If it has, then we cannot see ANDs
	public Converter(Expression g) {
		super(g);
	}
	
	protected void visitVariable(Variable o) {
		
	}
	
	protected void visitBinaryOp(BinaryOp o) {
		if(o.getTheBinaryOperator().compareTo(BinaryOperator.AND)==0)
		{
			if(OrSeen)
			{
				//need to emit a fresh variable and append an equivalence to the end of total expression
			}
		}
		
	}
	
	protected void visitUnaryOp(UnaryOp o){
		
	}


}
