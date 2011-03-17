package logic.model;

public class EF {
	
	public static Variable createVariable(String name){
		Variable var = new Variable();
		var.setTheName(name);
		return var;
	}
	
	public static BinaryOp createBinaryOp(Expression lhs, BinaryOperator op, Expression rhs){
		BinaryOp ret = new BinaryOp();
		ret.setTheBinaryOperator(op);
		ret.setTheLHS(lhs);
		ret.setTheRHS(rhs);
		return ret;
	}
	
	
	public static BinaryOp createAnd(Expression lhs, Expression rhs){
		return createBinaryOp(lhs, BinaryOperator.AND, rhs);
	}
	
	public static BinaryOp createOr(Expression lhs, Expression rhs){
		return createBinaryOp(lhs, BinaryOperator.OR, rhs);
	}
	
	public static BinaryOp createImp(Expression lhs, Expression rhs){
		return createBinaryOp(lhs, BinaryOperator.IMP, rhs);
	}
	
	public static BinaryOp createEquiv(Expression lhs, Expression rhs){
		return createBinaryOp(lhs, BinaryOperator.EQUIV, rhs);
	}
	
	public static BinaryOp createAnd(String lhs, Expression rhs){
		return createBinaryOp(createVariable(lhs), BinaryOperator.AND, rhs);
	}
	
	public static BinaryOp createOr(String lhs, Expression rhs){
		return createBinaryOp(createVariable(lhs), BinaryOperator.OR, rhs);
	}
	
	public static BinaryOp createImp(String lhs, Expression rhs){
		return createBinaryOp(createVariable(lhs), BinaryOperator.IMP, rhs);
	}
	
	public static BinaryOp createEquiv(String lhs, Expression rhs){
		return createBinaryOp(createVariable(lhs), BinaryOperator.EQUIV, rhs);
	}
	
	public static BinaryOp createAnd(String lhs, String rhs){
		return createBinaryOp(createVariable(lhs), BinaryOperator.AND, createVariable(rhs));
	}
	
	public static BinaryOp createOr(String lhs, String rhs){
		return createBinaryOp(createVariable(lhs), BinaryOperator.OR, createVariable(rhs));
	}
	
	public static BinaryOp createImp(String lhs, String rhs){
		return createBinaryOp(createVariable(lhs), BinaryOperator.IMP, createVariable(rhs));
	}
	
	public static BinaryOp createEquiv(String lhs, String rhs){
		return createBinaryOp(createVariable(lhs), BinaryOperator.EQUIV, createVariable(rhs));
	}
	
	public static BinaryOp createAnd(Expression lhs, String rhs){
		return createBinaryOp(lhs, BinaryOperator.AND, createVariable(rhs));
	}
	
	public static BinaryOp createOr(Expression lhs, String rhs){
		return createBinaryOp(lhs, BinaryOperator.OR, createVariable(rhs));
	}
	
	public static BinaryOp createImp(Expression lhs, String rhs){
		return createBinaryOp(lhs, BinaryOperator.IMP, createVariable(rhs));
	}
	
	public static BinaryOp createEquiv(Expression lhs, String rhs){
		return createBinaryOp(lhs, BinaryOperator.EQUIV, createVariable(rhs));
	}
	
	public static UnaryOp createNot(Expression e){
		UnaryOp not = new UnaryOp();
		not.setTheExpression(e);
		not.setTheOperator(UnaryOperator.NOT);
		return not;
	}
	
	public static UnaryOp createNot(String e){
		UnaryOp not = new UnaryOp();
		not.setTheExpression(createVariable(e));
		not.setTheOperator(UnaryOperator.NOT);
		return not;
	}
}
