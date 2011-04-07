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
		if(g.exMap.containsKey(o)){
			g.result=g.exMap.get(o);
		}
		else{
			visit(o.getTheLeftInput());
			Expression left = g.result;
			visit(o.getTheRightInput());
			Expression right = g.result;
			g.result=EF.createAnd(left, right);
			g.exMap.put(o, g.result);
		}
	}
	
	@Override
	protected void visitNot(Not o) {
		if(g.exMap.containsKey(o)){
			g.result=g.exMap.get(o);
		}
		else{
			visit(o.getTheExpression());
			g.result=EF.createNot(g.result);
			g.exMap.put(o, g.result);
		}
	}
	
	@Override
	protected void visitVariable(Variable o) {
		if(g.exMap.containsKey(o)){
			g.result=g.exMap.get(o);
		}
		else{
			g.result=EF.createVariable(String.valueOf(o.getTheVariableName()));
			g.exMap.put(o, g.result);
		}
	}
	
	@Override
	protected void visitLatch(Latch o) {
		visit(o.getTheCurrentState());
	}
	
	@Override
	protected void visitFalseLiteral(FalseLiteral o) {
		if(g.exMap.containsKey(o)){
			g.result =g.exMap.get(o);
		}
		else{
			g.result=EF.createFalseLiteral();
			g.exMap.put(o, g.result);
		}
	}
	
	@Override
	protected void visitTrueLiteral(TrueLiteral o) {
		if(g.exMap.containsKey(o)){
			g.result =g.exMap.get(o);
		}
		else{
			g.result=EF.createTrueLiteral();
			g.exMap.put(o, g.result);
		}
	}
	
	public static Expression CNFTranslate(aiger.model.Expression e){
		CNFTranslatorContext g = new CNFTranslatorContext();
		CNFTranslator v = new CNFTranslator(g);
		v.visit(e);
		return g.result;
	}
	
}
