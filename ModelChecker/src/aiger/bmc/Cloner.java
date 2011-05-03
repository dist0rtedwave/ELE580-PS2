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
		if(g.getTheCopies().containsKey(o)){
			g.setResult(g.getTheCopies().get(o));
		}
		else{
//			Variable v = new Variable(o.getTheVariableName() + g.offset);
			Variable v = new Variable(o.getTheVariableName());

			g.setResult(v);
			g.getTheCopies().put(o, v);
		}
	}
	
	@Override
	protected void visitNot(Not o) {
		if(g.getTheCopies().containsKey(o)){
			g.setResult(g.getTheCopies().get(o));
		}else{
			visit(o.getTheExpression());
			Not result = new Not(g.getResult());
			g.getTheCopies().put(o, result);
			g.setResult(result);
		}
	}
	
	@Override
	protected void visitAndGate(AndGate o) {
		if(g.getTheCopies().containsKey(o)){
			g.setResult(g.getTheCopies().get(o));
		}else{
			AndGate andGate = new AndGate(o.getTheName());
			visit(o.getTheLeftInput());
			andGate.setTheLeftInput(g.getResult());
			visit(o.getTheRightInput());
			andGate.setTheRightInput(g.getResult());
			g.getTheCopies().put(o, andGate);
			g.setResult(andGate);
		}
	}
	
	@Override
	protected void visitLatch(Latch o) {
		g.setResult(o.getTheCurrentState());
	}
	
	public static void clone(Latch l){
		ClonerContext g = new ClonerContext();
//		g.offset=k*maxVar;
		Cloner c = new Cloner(g);
		c.visit(l.getTheNextState());
		l.setTheInterimState(g.getResult());
	}
// Deep traversal of expression, making a deep copy. 
// Replaces latch with latch.unrolled
	
}
