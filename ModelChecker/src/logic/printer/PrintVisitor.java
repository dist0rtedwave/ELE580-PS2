package logic.printer;

import logic.model.*;

public class PrintVisitor extends Visitor<StringBuilder> {

	public PrintVisitor(StringBuilder g) {
		super(g);
	}
	
	protected void visitVariable(Variable o) {
		g.append(o.getTheName());
	}
	
	protected void visitBinaryOp(BinaryOp o) {
		g.append(" (");
		visit(o.getTheLHS());
		g.append(") ");
		g.append(o.getTheBinaryOperator());
		g.append(" (");
		visit(o.getTheRHS());
		g.append(") ");
	}
	
	protected void visitUnaryOp(UnaryOp o){
		g.append(o.getTheOperator());
		g.append(" (");
		visit(o.getTheExpression());
		g.append(") ");
	}

	public static String expressionToString(Expression e){
		StringBuilder b = new StringBuilder();
		PrintVisitor v = new PrintVisitor(b);
		v.visit(e);
		return b.toString();
	}

}
