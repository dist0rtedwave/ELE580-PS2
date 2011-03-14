package logic.converter;

/* will take an expression and create an expression with equivalences that the tseitin2cnf will handle*/

import logic.model.*;

public class Converter extends Visitor<Expression> {

	public Converter(Expression g) {
		super(g);
	}
	
	protected void visitVariable(Variable o) {
		
	}
	
	protected void visitBinaryOp(BinaryOp o) {
		
	}
	
	protected void visitUnaryOp(UnaryOp o){
		
	}


}
