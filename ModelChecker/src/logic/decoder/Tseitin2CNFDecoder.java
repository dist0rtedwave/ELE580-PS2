package logic.decoder;

import logic.model.BinaryOp;
import logic.model.BinaryOperator;
import logic.model.Expression;
import logic.model.UnaryOp;
import logic.model.UnaryOperator;
import logic.model.Variable;
import logic.model.Visitor;

public class Tseitin2CNFDecoder extends Visitor<BinaryOp>{

	public Tseitin2CNFDecoder(BinaryOp g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	public void visit(Expression o){
		if(o == null)
			return;
		switch (o.getDescriptor()){
		case Variable.DESCRIPTOR:
			visitVariable((Variable)o);
			break;
		case BinaryOp.DESCRIPTOR:
			visitBinaryOp((BinaryOp)o);
			break;
		case UnaryOp.DESCRIPTOR:
			visitUnaryOp((UnaryOp)o);
			break;
		}
	}

	protected void visitExpression(Expression o){
		assert o!=null;
	}
	
	protected void visitVariable(Variable o) {
		assert o != null;
		visitExpression(o);
	}
	
	/*IMPORTANT: see slides for reasons why i assigned variables the way I did.*/
	
	
	//x <=> notY
	Expression UnaryTseitin(Expression X, UnaryOp notY)
	{
		BinaryOp ret_Total = new BinaryOp();
		BinaryOp ret_LHS = new BinaryOp();
		BinaryOp ret_RHS = new BinaryOp();
		
		UnaryOp notX = new UnaryOp();  	//not x
		notX.setTheOperator(UnaryOperator.NOT);
		notX.setTheExpression(X);
		
		UnaryOp Y = new UnaryOp();  	//y
		Y.setTheOperator(UnaryOperator.NOT);
		Y.setTheExpression(notY);
		
		ret_LHS.setTheLHS(notX);
		ret_LHS.setTheLHS(notY);
		//(NOT x \/ NOT y)
		ret_LHS.setTheBinaryOperator(BinaryOperator.OR);
		
		ret_RHS.setTheLHS(Y);
		ret_RHS.setTheLHS(X);
		//( x \/  y)
		ret_RHS.setTheBinaryOperator(BinaryOperator.OR);
		
		ret_Total.setTheLHS(ret_LHS);
		ret_Total.setTheRHS(ret_RHS);
		//(NOT x \/ NOT y) /\ ( x \/  y)
		ret_Total.setTheBinaryOperator(BinaryOperator.AND);
		
		return ret_Total;
	}
	
	// x<=>RHS
	Expression BinaryTseitin(Expression X, BinaryOp RHS)
	{
		Expression Y = RHS.getTheLHS();
		Expression Z = RHS.getTheRHS();
		
		UnaryOp notX = new UnaryOp();  	//NOT X
		notX.setTheOperator(UnaryOperator.NOT);
		notX.setTheExpression(X);
		
		UnaryOp notY = new UnaryOp();  	//NOT Y
		notY.setTheOperator(UnaryOperator.NOT);
		notY.setTheExpression(Y);
		
		UnaryOp notZ = new UnaryOp();  	//NOT Z
		notZ.setTheOperator(UnaryOperator.NOT);
		notZ.setTheExpression(Z);
		
		BinaryOp t0 = new BinaryOp();
		t0.setTheBinaryOperator(BinaryOperator.OR);
		
		BinaryOp t1 = new BinaryOp();
		t1.setTheBinaryOperator(BinaryOperator.OR);
		
		BinaryOp t2a = new BinaryOp();
		t2a.setTheBinaryOperator(BinaryOperator.OR);
		
		BinaryOp t2b = new BinaryOp();
		t2b.setTheBinaryOperator(BinaryOperator.OR);
		
		BinaryOp t0_1 = new BinaryOp();
		t0_1.setTheBinaryOperator(BinaryOperator.AND);
		
		BinaryOp t0_1_2 = new BinaryOp();
		t0_1_2.setTheBinaryOperator(BinaryOperator.AND);
		
		//DISJUNCTION!
		if(RHS.getTheBinaryOperator().compareTo(BinaryOperator.OR)==0)
		{
			//(notY OR X)
			t0.setTheLHS(notY);
			t0.setTheRHS(X);
		
			//(notZ OR X)
			t1.setTheLHS(notZ);
			t1.setTheRHS(X);
			
			//(notX OR Y)
			t2a.setTheLHS(notX);
			t2a.setTheRHS(Y);
			
			//(notX OR Y OR Z)
			t2b.setTheLHS(t2a);
			t2b.setTheRHS(Z);
			
			//(notY OR X) /\  (notZ OR X)
			t0_1.setTheLHS(t0);
			t0_1.setTheRHS(t1);
			
			//(notY OR X) /\  (notZ OR X) /\ (notX OR Y OR Z)
			t0_1_2.setTheLHS(t0_1);
			t0_1_2.setTheRHS(t2b);
		}
		else		//CONJUNCTION
		{
			//(Y OR notX)
			t0.setTheLHS(Y);
			t0.setTheRHS(notX);
		
			//(Z OR notX)
			t1.setTheLHS(Z);
			t1.setTheRHS(notX);
			
			//(notY OR notZ)
			t2a.setTheLHS(notY);
			t2a.setTheRHS(notZ);
			
			//(notY OR notZ OR X)
			t2b.setTheLHS(t2a);
			t2b.setTheRHS(X);
			
			//(Y OR notX)/\ (Z OR notX)
			t0_1.setTheLHS(t0);
			t0_1.setTheRHS(t1);
			
			//(Y OR notX)/\ (Z OR notX) /\ (notY OR notZ OR X)
			t0_1_2.setTheLHS(t0_1);
			t0_1_2.setTheRHS(t2b);
		}
		return t0_1_2;
	}
	
	// x <==> y
	Expression VariableTseitin(Expression X, Variable Y)
	{
		BinaryOp ret_Total = new BinaryOp();
		ret_Total.setTheBinaryOperator(BinaryOperator.AND);
		
		BinaryOp ret_LHS = new BinaryOp();
		ret_LHS.setTheBinaryOperator(BinaryOperator.OR);
		
		BinaryOp ret_RHS = new BinaryOp();
		ret_RHS.setTheBinaryOperator(BinaryOperator.OR);
		
		UnaryOp notX = new UnaryOp();  	//notX
		notX.setTheOperator(UnaryOperator.NOT);
		notX.setTheExpression(X);
		
		UnaryOp notY = new UnaryOp();  	//notY
		notY.setTheOperator(UnaryOperator.NOT);
		notY.setTheExpression(notY);
		
		//(y \/ notX)
		ret_LHS.setTheLHS(Y);
		ret_LHS.setTheLHS(notX);
				
		//( x \/  notY)
		ret_RHS.setTheLHS(notY);
		ret_RHS.setTheLHS(X);
			
		//( x \/  notY) /\ (y \/ notX)
		ret_Total.setTheLHS(ret_LHS);
		ret_Total.setTheRHS(ret_RHS);

		return ret_Total;
		
	}
	
	
	protected void visitBinaryOp(BinaryOp o) {
		assert o != null;
		if(o.getTheBinaryOperator().compareTo(BinaryOperator.EQUIV) == 0)
		{
			Expression rhs = o.getTheRHS();
			Expression retVal = null;
			switch (rhs.getDescriptor()){
			case Variable.DESCRIPTOR:
				retVal = VariableTseitin(o.getTheLHS(), (Variable)rhs);
				break;
			case BinaryOp.DESCRIPTOR:
				retVal = BinaryTseitin(o.getTheLHS(), (BinaryOp)rhs);
				break;
			case UnaryOp.DESCRIPTOR:
				retVal = UnaryTseitin(o.getTheLHS(), (UnaryOp)rhs);
				break;
			}
			BinaryOp newExpr = new BinaryOp();
			newExpr.setTheLHS(g);
			newExpr.setTheRHS(retVal);
			newExpr.setTheBinaryOperator(BinaryOperator.AND);
			g = newExpr;
		}
		visit(o.getTheLHS());
		visit(o.getTheRHS());
		visitExpression(o);
	}
	
	protected void visitUnaryOp(UnaryOp o){
		assert o!=null;
		visitExpression(o);
	}
}
