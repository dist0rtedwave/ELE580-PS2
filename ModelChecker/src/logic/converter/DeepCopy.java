package logic.converter;

/* will take an expression and create an expression with equivalences that the tseitin2cnf will handle*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import logic.converter.test.CycleChecker;
import logic.model.BinaryOp;
import logic.model.BinaryOperator;
import logic.model.EF;
import logic.model.Expression;
import logic.model.FalseLiteral;
import logic.model.TrueLiteral;
import logic.model.UnaryOp;
import logic.model.Variable;
import logic.model.Visitor;
import logic.printer.PrintVisitor;

public class DeepCopy extends Visitor<DeepCopyContext> {

	
	
	public static Expression deepCopy(Expression e){
		DeepCopyContext context = new DeepCopyContext();
		context.visitResult = null;
		DeepCopy con = new DeepCopy(context);
		con.visit(e);  
		return context.visitResult;
	}
	
	public DeepCopy(DeepCopyContext context) {
		super(context);
	}
	
	@Override
	protected void visitUnaryOp(UnaryOp o) {
		if(g.exprCache.containsKey(o)){
			g.visitResult=g.exprCache.get(o);
			return;
		}	
	
		UnaryOp newUnOp = new UnaryOp();
		visit(o.getTheExpression());
	    newUnOp.setTheOperator(o.getTheOperator());
		newUnOp.setTheExpression(g.visitResult);
		g.exprCache.put(o, newUnOp);
		g.visitResult = newUnOp;
		return;
	}
	
	@Override
	protected void visitVariable(Variable o) {
		if(g.exprCache.containsKey(o)){
			g.visitResult=g.exprCache.get(o);
			return;
		}	
		Variable newV = EF.createVariable(o.getTheName());
		g.exprCache.put(o,newV);
		this.g.visitResult = newV;
		return;
	}
	
	//assumes only and and or
	protected void visitBinaryOp(BinaryOp o) {
		if(g.exprCache.containsKey(o)){
			g.visitResult=g.exprCache.get(o);
			return;
		}	
		BinaryOp newBinOp = new BinaryOp();
		newBinOp.setTheBinaryOperator(o.getTheBinaryOperator());
		visit(o.getTheLHS());
		newBinOp.setTheLHS(g.visitResult);
		
		visit(o.getTheRHS());
		newBinOp.setTheRHS(g.visitResult);
		

		g.exprCache.put(o, newBinOp);		
		
		
		g.visitResult = newBinOp;
	}
	
	@Override
	protected void visitFalseLiteral(FalseLiteral o) {
		if(g.exprCache.containsKey(o)){
			g.visitResult=g.exprCache.get(o);
			return;
		}	
		FalseLiteral newLit = EF.createFalseLiteral();
		g.exprCache.put(o,newLit);
		this.g.visitResult = newLit;
		return;
	}
	
	@Override
	protected void visitTrueLiteral(TrueLiteral o) {
		if(g.exprCache.containsKey(o)){
			g.visitResult=g.exprCache.get(o);
			return;
		}	
		TrueLiteral newLit = EF.createTrueLiteral();
		g.exprCache.put(o,newLit);
		this.g.visitResult = newLit;
		return;
	}
	
}
