package interpolant.checker;

import logic.model.BinaryOp;
import logic.model.Expression;
import logic.model.FalseLiteral;
import logic.model.TrueLiteral;
import logic.model.UnaryOp;
import logic.model.Variable;
import logic.model.Visitor;

public class TimeShifter extends Visitor<TimeShifterContext> {

	public static void timeShift(Expression e, int maxVarIndex)
	{
		TimeShifterContext cntxt = new TimeShifterContext();
		cntxt.maxVariableIndex = maxVarIndex;
		TimeShifter shifter = new TimeShifter(cntxt);
		shifter.visit(e);
	}
	
	@Override
	protected void visitVariable(Variable o) {
		if(o.getTheName().startsWith("_t"))
			return;
		else if(o.getTheName().startsWith("_l"))
		{
			int tIndex = o.getTheName().lastIndexOf("_");
			String newName  = o.getTheName().substring(0, tIndex+1);
			o.setTheName(newName + "0");
			
		}
		else
		{
			int i = Integer.valueOf(o.getTheName());
			o.setTheName(String.valueOf(i % g.getMaxVariableIndex()));
			return;
		}
	}

	protected void visitBinaryOp(BinaryOp o) {
		if(g.exprCache.containsKey(o)){
			return;
		}	
		visit(o.getTheLHS());
		visit(o.getTheRHS());
		g.exprCache.put(o,o);
		return;
	}

	protected void visitUnaryOp(UnaryOp o) {
		if(g.exprCache.containsKey(o)){
			return;
		}
		visit(o.getTheExpression());
		g.exprCache.put(o,o);
		return;

	}
	 
	public TimeShifter(TimeShifterContext g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

}
