package logic.converter;

import logic.model.BinaryOp;
import logic.model.BinaryOperator;
import logic.model.EF;
import logic.model.Expression;
import logic.model.FalseLiteral;
import logic.model.TrueLiteral;
import logic.model.UnaryOp;
import logic.model.UnaryOperator;
import logic.model.Variable;
import logic.model.Visitor;

public class LiteralSimplifier extends Visitor<LiteralSimplifierContext>{

	public static Expression simplifyLiterals(Expression e){
		LiteralSimplifierContext context = new LiteralSimplifierContext();
		LiteralSimplifier simplifier = new LiteralSimplifier(context);
		simplifier.visit(e);
		return context.result;
	}
	
	
	public LiteralSimplifier(LiteralSimplifierContext g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void visitBinaryOp(BinaryOp o) {
		visit(o.getTheLHS());
		boolean left = g.isLiteral;
		Expression lhs = g.result;
		boolean leftLiteral = g.theLiteral;
		visit(o.getTheRHS());
		boolean right = g.isLiteral;
		if(o.getTheBinaryOperator() == BinaryOperator.AND){
			if((left && !leftLiteral) || (right && !g.theLiteral)){
				g.result=EF.createFalseLiteral();
				g.theLiteral=false;
				g.isLiteral=true;
				return;
			}
			if(left&& leftLiteral){
				return;
			}
			if(right && g.theLiteral){
				g.isLiteral=left;
				g.theLiteral=leftLiteral;
				g.result=lhs;
				return;
			}
		}
		if(o.getTheBinaryOperator()==BinaryOperator.OR){
			if((left && leftLiteral)|| (right && g.theLiteral)){
				g.result=EF.createTrueLiteral();
				g.theLiteral=true;
				g.isLiteral=true;
				return;
			}
			if(left&& !leftLiteral){
				return;
			}
			if(right && !g.theLiteral){
				g.isLiteral=left;
				g.theLiteral=leftLiteral;
				g.result=lhs;
				return;
			}
		}
		if(left && right)
		{
			if(o.getTheBinaryOperator()==BinaryOperator.AND){
				g.theLiteral = leftLiteral && g.theLiteral;
			}
			else{
				g.theLiteral = leftLiteral || g.theLiteral;
			}
			if(g.theLiteral)
				g.result = EF.createTrueLiteral();
			else
				g.result = EF.createFalseLiteral();
		}
		else
		{
			o.setTheLHS(lhs);
			o.setTheRHS(g.result);
			g.result = o;
			g.isLiteral = false;
		}
		
	}
	
	@Override
	protected void visitFalseLiteral(FalseLiteral o) {
		g.isLiteral = true;
		g.theLiteral = false;
		g.result = o;
	}
	
	@Override
	protected void visitTrueLiteral(TrueLiteral o) {
		g.isLiteral = true;
		g.theLiteral = true;
		g.result = o;
	}
	

	@Override
	protected void visitVariable(Variable o) {
		g.isLiteral = false;
		g.result=o;
	}
	
	@Override
	protected void visitUnaryOp(UnaryOp o) {
		visit(o.getTheExpression());
		if (g.isLiteral && g.theLiteral)
		{
			g.result = EF.createFalseLiteral();
			g.isLiteral = true;
			g.theLiteral = false;
			return;
		}
		else if(g.isLiteral && !g.theLiteral)
		{
			g.result = EF.createTrueLiteral();
			g.isLiteral = true;
			g.theLiteral = true;
			return;
		}
		g.isLiteral = false;
		g.result = o;
	}

}
