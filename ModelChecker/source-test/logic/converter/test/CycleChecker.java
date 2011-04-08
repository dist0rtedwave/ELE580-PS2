package logic.converter.test;

import logic.model.BinaryOp;
import logic.model.Expression;
import logic.model.UnaryOp;
import logic.model.Visitor;

public class CycleChecker extends Visitor<CycleCheckerContext>{

	public static boolean checkForCycles(Expression e){
		CycleCheckerContext g = new CycleCheckerContext();
		CycleChecker cc = new CycleChecker(g);
		cc.visit(e);
		return g.cycleDetected;
	}
	
	public CycleChecker(CycleCheckerContext g) {
		super(g);
	}
	
	@Override
	protected void visitBinaryOp(BinaryOp o) {
		if(g.seen.contains(o)){
			g.cycleDetected=true;
		}
		else{
			g.seen.add(o);
			visit(o.getTheLHS());
			visit(o.getTheRHS());
			g.seen.remove(o);
		}
	}
	
	@Override
	protected void visitUnaryOp(UnaryOp o) {
		if(g.seen.contains(o)){
			g.cycleDetected=true;
		}
		else{
			g.seen.add(o);
			visit(o.getTheExpression());
			g.seen.remove(o);
		}
	}
	
}
