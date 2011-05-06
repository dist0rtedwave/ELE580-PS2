package aiger.bmc;

import interpolant.checker.ModelChecker;

import java.util.Map;

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
	
	public static Expression convertToLogicExpressionWithLatchesAsVariables(aiger.model.Expression e, int currentTime)
	{
		CNFTranslatorContext g = new CNFTranslatorContext();
		g.latchesAsVariables = true;
		g.currentTime = currentTime;
		CNFTranslator v = new CNFTranslator(g);
		v.visit(e);
		return g.result;
	}
	
	private Map<aiger.model.Expression, Expression> selectMap(){
		if(g.inLatch){
			return g.latchExMap;
		}
		else{
			return g.exMap;
		}
	}
	
	@Override
	protected void visitAndGate(AndGate o) {
		Map<aiger.model.Expression, Expression> map = selectMap();
	
		if(map.containsKey(o)){
			g.result=map.get(o);
		}
		else{
			visit(o.getTheLeftInput());
			Expression left = g.result;
			visit(o.getTheRightInput());
			Expression right = g.result;
			g.result=EF.createAnd(left, right);
			map.put(o, g.result);
		}
	}
	
	@Override
	protected void visitNot(Not o) {
		Map<aiger.model.Expression, Expression> map = selectMap();
		if(map.containsKey(o)){
			g.result=map.get(o);
		}
		else{
			visit(o.getTheExpression());
			g.result=EF.createNot(g.result);
			map.put(o, g.result);
		}
	}
	
	@Override
	protected void visitVariable(Variable o) {
		Map<aiger.model.Expression, Expression> map = selectMap();
		if(map.containsKey(o)){
			g.result=map.get(o);
		}
		else{
			if(g.inLatch){
				g.result=EF.createVariable(String.valueOf(o.getTheVariableName()));
			}
			else{
				g.result=EF.createVariable(String.valueOf(o.getTheVariableName()+g.offset));
			}
			map.put(o, g.result);
		}
	}
	
	@Override
	protected void visitLatch(Latch o) {
		if(g.latchesAsVariables)
		{	
			if(g.latchExMap.containsKey(o)){  //doesnt matter which map we use
				g.result=g.latchExMap.get(o);
			}
			visit(o.getTheCurrentState());
			Expression var = EF.createVariable("_l"+ModelChecker.id2latch.get(o)+"_"+g.currentTime);
			g.result = var;
			g.latchExMap.put(o, g.result);
		}
		else
		{
			g.inLatch=true;
			visit(o.getTheCurrentState());
			g.inLatch=false;
		}
	}
	
	@Override
	protected void visitFalseLiteral(FalseLiteral o) {
		Map<aiger.model.Expression, Expression> map = selectMap();
		if(map.containsKey(o)){
			g.result =map.get(o);
		}
		else{
			g.result=EF.createFalseLiteral();
			map.put(o, g.result);
		}
	}
	
	@Override
	protected void visitTrueLiteral(TrueLiteral o) {		
		Map<aiger.model.Expression, Expression> map = selectMap();
		if(map.containsKey(o)){
			g.result =map.get(o);
		}
		else{
			g.result=EF.createTrueLiteral();
			map.put(o, g.result);
		}
	}
	
	public static Expression CNFTranslate(aiger.model.Expression e, int offset){
		CNFTranslatorContext g = new CNFTranslatorContext();
		g.offset=offset;
		CNFTranslator v = new CNFTranslator(g);
		v.visit(e);
		return g.result;
	}
	
	public static Expression CNFTranslate(aiger.model.Expression e){
		CNFTranslatorContext g = new CNFTranslatorContext();
		g.inLatch = true;
		CNFTranslator v = new CNFTranslator(g);
		v.visit(e);
		return g.result;
	}
	
}
