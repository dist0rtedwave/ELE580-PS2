package aiger.bmc;

import logic.model.EF;
import logic.model.Expression;
import aiger.model.AndGate;
import aiger.model.FalseLiteral;
import aiger.model.Latch;
import aiger.model.Not;
import aiger.model.TrueLiteral;
import aiger.model.Variable;
import aiger.model.Visitor;

public class CNFTranslator extends Visitor<CNFTranslatorContext> {

	public CNFTranslator(CNFTranslatorContext g) {
		super(g);
	}
	
	@Override
	protected void visitAndGate(AndGate o) {
		visit(o.getTheLeftInput());
		Expression left = g.result;
		visit(o.getTheRightInput());
		Expression right = g.result;
		g.result=EF.createAnd(left, right);
	}
	
	@Override
	protected void visitNot(Not o) {
		visit(o.getTheExpression());
		g.result=EF.createNot(g.result);
	}
	
	@Override
	protected void visitVariable(Variable o) {
		g.result=EF.createVariable(String.valueOf(o.getTheVariableName()));
	}
	
	@Override
	protected void visitLatch(Latch o) {
		visit(o.getTheCurrentState());
	}
	
	@Override
	protected void visitFalseLiteral(FalseLiteral o) {
		g.result=EF.createFalseLiteral();
	}
	
	@Override
	protected void visitTrueLiteral(TrueLiteral o) {
		g.result=EF.createFalseLiteral();
	}
	
	public static Expression CNFTranslate(aiger.model.Expression e){
		CNFTranslatorContext g = new CNFTranslatorContext();
		CNFTranslator v = new CNFTranslator(g);
		v.visit(e);
		return g.result;
	}
	
}
