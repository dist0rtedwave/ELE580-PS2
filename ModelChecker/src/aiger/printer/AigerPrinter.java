package aiger.printer;

import aiger.model.AndGate;
import aiger.model.Variable;
import aiger.model.Visitor;

public class AigerPrinter extends Visitor<StringBuilder> {

	public AigerPrinter(StringBuilder g) {
		super(g);
	}
	
	@Override
	protected void visitVariable(Variable o) {
		g.append(o.getTheVariableName());
	}
	
	@Override
	protected void visitAndGate(AndGate o) {
		g.append("(");
		visit(o.getTheLeftInput());
		g.append(" /\\ ");
	}
	
}
