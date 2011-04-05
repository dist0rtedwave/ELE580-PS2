package aiger.bmc;

import aiger.model.AndGate;
import aiger.model.Latch;
import aiger.model.Not;
import aiger.model.Variable;
import aiger.model.Visitor;

public class Cloner extends Visitor<ClonerContext>{

	public Cloner(ClonerContext g) {
		super(g);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void visitVariable(Variable o) {
		g.setResult(o);
	}
	
	@Override
	protected void visitNot(Not o) {
		Not result = new Not(null);
//		g.getTheCopies().put(o, result);
//		if(g.getTheCopies().containsKey(o.getTheExpression())){
//			result.setTheExpression(g.getTheCopies().get(o.getTheExpression()));
//		}else{
			visit(o.getTheExpression());
			result.setTheExpression(g.getResult());
//		}
		g.setResult(result);
	}
	
	@Override
	protected void visitAndGate(AndGate o) {
		AndGate result = new AndGate(o.getTheName());
//		g.getTheCopies().put(o, result);
//		if(g.getTheCopies().containsKey(o.getTheLeftInput())){
//			result.setTheLeftInput(this.g.getTheCopies().get(o.getTheLeftInput()));
//		}
//		else{
			visit(o.getTheLeftInput());
			result.setTheLeftInput(g.getResult());
//		}
//		if(g.getTheCopies().containsKey(o.getTheRightInput())){
//			result.setTheRightInput(this.g.getTheCopies().get(o.getTheRightInput()));
//		}
//		else{
			visit(o.getTheRightInput());
			result.setTheRightInput(g.getResult());
//		}
		g.setResult(result);
	}
	
	@Override
	protected void visitLatch(Latch o) {
		g.setResult(o.getTheCurrentState());
	}
	
	public static void clone(Latch l){
		ClonerContext g = new ClonerContext();
		Cloner c = new Cloner(g);
		c.visit(l.getTheNextState());
		l.setTheInterimState(g.getResult());
	}
// Deep traversal of expression, making a deep copy. 
// Replaces latch with latch.unrolled
	
}
