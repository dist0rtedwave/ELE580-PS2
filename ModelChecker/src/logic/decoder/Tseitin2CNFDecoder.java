package logic.decoder;

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

public class Tseitin2CNFDecoder extends Visitor<TseitinDecoderContext>{

	public Tseitin2CNFDecoder(TseitinDecoderContext g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	public static Expression decode(Expression o){
		
		if(o instanceof TrueLiteral)
			return o;
		if(o instanceof FalseLiteral)
			return o;
		if(o==null)
			return o;
		
		if(o.getDescriptor()==UnaryOp.DESCRIPTOR)
			return o;
		if(o.getDescriptor()==Variable.DESCRIPTOR)
			return o;
		TseitinDecoderContext context = new TseitinDecoderContext();
		Tseitin2CNFDecoder dist = new Tseitin2CNFDecoder(context);
		dist.visit(o);
		return context.getChild();
	}

	
	
	protected void visitVariable(Variable o) {
		this.g.setChild(o);
	}
	
	/*IMPORTANT: see slides for reasons why i assigned variables the way I did.*/
	
	
//	//x <=> notY
//	BinaryOp UnaryTseitin(Expression X, UnaryOp notY)
//	{
//		
//		BinaryOp ret_Total = new BinaryOp();
//		BinaryOp ret_LHS = new BinaryOp();
//		BinaryOp ret_RHS = new BinaryOp();
//		
//		UnaryOp notX = new UnaryOp();  	//not x
//		notX.setTheOperator(UnaryOperator.NOT);
//		notX.setTheExpression(X);
//		
//		UnaryOp Y = new UnaryOp();  	//y
//		Y.setTheOperator(UnaryOperator.NOT);
//		Y.setTheExpression(notY);
//		
//		ret_LHS.setTheLHS(notX);
//		ret_LHS.setTheRHS(notY);
//		//(NOT x \/ NOT y)
//		ret_LHS.setTheBinaryOperator(BinaryOperator.OR);
//		
//		ret_RHS.setTheLHS(Y);
//		ret_RHS.setTheRHS(X);
//		//( x \/  y)
//		ret_RHS.setTheBinaryOperator(BinaryOperator.OR);
//		
//		ret_Total.setTheLHS(ret_LHS);
//		ret_Total.setTheRHS(ret_RHS);
//		//(NOT x \/ NOT y) /\ ( x \/  y)
//		ret_Total.setTheBinaryOperator(BinaryOperator.AND);
//		
//		return ret_Total;
//	}
	
	// x<=>RHS
	BinaryOp BinaryTseitin(Expression X, BinaryOp RHS)
	{
		Expression Y = RHS.getTheLHS();
		Expression Z = RHS.getTheRHS();
		
		UnaryOp notX = EF.createNot(X);
		UnaryOp notY = EF.createNot(Y);
		UnaryOp notZ = EF.createNot(Z);
		
		BinaryOp ret = null;
		
		//DISJUNCTION!
		if(RHS.getTheBinaryOperator().compareTo(BinaryOperator.OR)==0)
		{
			//(notY OR X)
			BinaryOp notY_o_X = EF.createOr(notY, X);
		
			//(notZ OR X)
			BinaryOp notZ_o_X = EF.createOr(notZ, X);
			
			//(notX OR Y)
			BinaryOp notX_o_Y = EF.createOr(notX, Y);
			
			//(notX OR Y OR Z)
			BinaryOp notX_o_Y_o_Z = EF.createOr(notX_o_Y, Z);
			
			//(notY OR X) /\  (notZ OR X) /\ (notX OR Y OR Z)
			ret = EF.createAnd(EF.createAnd(notY_o_X, notZ_o_X), notX_o_Y_o_Z);
			
		}
		else		//CONJUNCTION
		{
			//(Y OR notX)
			BinaryOp Y_o_notX = EF.createOr(Y, notX);
		
			//(Z OR notX)
			BinaryOp Z_o_notX = EF.createOr(Z, notX);
			
			//(notY OR notZ)
			BinaryOp notY_o_notZ = EF.createOr(notY, notZ);
			
			//(notY OR notZ OR X)
			BinaryOp notY_o_notZ_o_X = EF.createOr(notY_o_notZ, X);
			
			//(Y OR notX)/\ (Z OR notX) /\ (notY OR notZ OR X)
			ret = EF.createAnd(EF.createAnd(Y_o_notX, Z_o_notX), notY_o_notZ_o_X);
		}
		return ret;
	}
	
//	// x <==> y
//	BinaryOp VariableTseitin(Expression X, Variable Y)
//	{
//		BinaryOp ret_Total = new BinaryOp();
//		ret_Total.setTheBinaryOperator(BinaryOperator.AND);
//		
//		BinaryOp ret_LHS = new BinaryOp();
//		ret_LHS.setTheBinaryOperator(BinaryOperator.OR);
//		
//		BinaryOp ret_RHS = new BinaryOp();
//		ret_RHS.setTheBinaryOperator(BinaryOperator.OR);
//		
//		UnaryOp notX = new UnaryOp();  	//notX
//		notX.setTheOperator(UnaryOperator.NOT);
//		notX.setTheExpression(X);
//		
//		UnaryOp notY = new UnaryOp();  	//notY
//		notY.setTheOperator(UnaryOperator.NOT);
//		notY.setTheExpression(Y);
//		
//		//(y \/ notX)
//		ret_LHS.setTheLHS(Y);
//		ret_LHS.setTheRHS(notX);
//				
//		//( x \/  notY)
//		ret_RHS.setTheLHS(notY);
//		ret_RHS.setTheRHS(X);
//			
//		//( x \/  notY) /\ (y \/ notX)
//		ret_Total.setTheLHS(ret_LHS);
//		ret_Total.setTheRHS(ret_RHS);
//
//		return ret_Total;
//		
//	}
	
	protected void visitBinaryOp(BinaryOp o) {
		if(o.getTheBinaryOperator().compareTo(BinaryOperator.EQUIV) == 0)
		{
			
			Expression rhs = o.getTheRHS();
			BinaryOp retVal = null;
			switch (rhs.getDescriptor()){
	//		case Variable.DESCRIPTOR:
	//			retVal = VariableTseitin(o.getTheLHS(), (Variable)rhs);
	//			break;
			case BinaryOp.DESCRIPTOR:
				retVal = BinaryTseitin(o.getTheLHS(), (BinaryOp)rhs);
				break;
			default:
				throw new Error("Decoder encountered a non-binary op");

			}
			
			//append new expression to g
	
			o.setTheLHS(retVal.getTheLHS());
			o.setTheBinaryOperator(retVal.getTheBinaryOperator());
			o.setTheRHS(retVal.getTheRHS());

		}
		visit(o.getTheLHS());
		o.setTheLHS(this.g.getChild());
		visit(o.getTheRHS());
		o.setTheRHS(this.g.getChild());
		this.g.setChild(o);	
	
		
		
		
	}
	
	protected void visitUnaryOp(UnaryOp o){
		//visit(o.getTheExpression());
		this.g.setChild(o);
	}
	
	protected void visitFalseLiteral(FalseLiteral o){
		//visit(o.getTheExpression());
		this.g.setChild(o);
	}
	
	protected void visitTrueLiteral(TrueLiteral o){
		//visit(o.getTheExpression());
		this.g.setChild(o);
	}
}
