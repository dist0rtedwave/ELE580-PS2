package aiger.bmc;

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
		g.inLatch=true;
		visit(o.getTheCurrentState());
		g.inLatch=false;
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
	
}
