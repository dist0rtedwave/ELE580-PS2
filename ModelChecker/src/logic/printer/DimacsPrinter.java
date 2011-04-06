package logic.printer;

import logic.model.*;
import java.util.HashMap;

public class DimacsPrinter extends Visitor<StringBuilder> {
	
	protected HashMap<String, Integer> nameMap;
	protected int nameCounter;
	protected int andCounter;
	

	public DimacsPrinter(StringBuilder g) {
		super(g);
		this.nameMap = new HashMap<String, Integer>();
		this.nameCounter = 1;
		this.andCounter = 0;
	}
	
	protected void visitVariable(Variable o) {
		if (!this.nameMap.containsKey(o.getTheName()))
		{
			this.nameMap.put(o.getTheName(), new Integer(this.nameCounter));
			this.nameCounter += 1;
		}
		g.append(this.nameMap.get(o.getTheName())+" ");
	}
	
	protected void visitBinaryOp(BinaryOp o) {
		visit(o.getTheLHS());
		if(o.getTheBinaryOperator() == BinaryOperator.AND)
		{
			g.append("0 \n");
			this.andCounter += 1;
		}
		visit(o.getTheRHS());
	}
	
	protected void visitUnaryOp(UnaryOp o){
		if(o.getTheOperator()==UnaryOperator.NOT)
		{
			g.append("-");
		}
		visit(o.getTheExpression());
	}

	public String expressionToString(Expression e){		
		this.visit(e);
		g.append("0 \n");
		
		return "c "+PrintVisitor.expressionToString(e)+"\n"+"p cnf "+ (this.nameCounter-1) +" "+ (this.andCounter+1)+"\n"+g.toString();
	}

}
