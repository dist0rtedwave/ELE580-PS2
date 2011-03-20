
package logic.test.examples;

import logic.model.BinaryOp;
import logic.model.BinaryOperator;
import logic.model.EF;
import logic.model.Expression;
import logic.model.UnaryOp;
import logic.model.UnaryOperator;
import logic.model.Variable;

public class Examples {
	
	public static Expression getExample1(){
		Variable varA = new Variable();
		varA.setTheName("a");
		Variable varB = new Variable();
		varB.setTheName("b");
		
		Variable varC = new Variable();
		varC.setTheName("c");
		Variable varD = new Variable();
		varD.setTheName("d");
		
		BinaryOp bop1 = new BinaryOp();
		bop1.setTheLHS(varA);
		bop1.setTheRHS(varB);
		bop1.setTheBinaryOperator(BinaryOperator.AND);
		
		BinaryOp bop2 = new BinaryOp();
		UnaryOp uop1 = new UnaryOp();
		uop1.setTheExpression(varD);
		uop1.setTheOperator(UnaryOperator.NOT);
		bop2.setTheLHS(varC);
		bop2.setTheRHS(uop1);	
		bop2.setTheBinaryOperator(BinaryOperator.AND);
		
		BinaryOp topLevelExpr = new BinaryOp();
		topLevelExpr.setTheLHS(bop1);
		topLevelExpr.setTheRHS(bop2);
		topLevelExpr.setTheBinaryOperator(BinaryOperator.OR);
		
		return topLevelExpr;
	}
	
	// a <=> (b/\c)
	public static Expression getExample2(){
		Variable varA = new Variable();
		varA.setTheName("a");
		Variable varB = new Variable();
		varB.setTheName("b");
		Variable varC = new Variable();
		varC.setTheName("c");
		
		BinaryOp bop1 = new BinaryOp();
		bop1.setTheLHS(varB);
		bop1.setTheRHS(varC);
		bop1.setTheBinaryOperator(BinaryOperator.AND);
		
		BinaryOp bop2 = new BinaryOp();
		bop2.setTheLHS(varA);
		bop2.setTheRHS(bop1);	
		bop2.setTheBinaryOperator(BinaryOperator.EQUIV);
		
		
		return bop2;
	}
	
	// a <=> (b\/c)
	public static BinaryOp getExample3(){
		Variable varA = new Variable();
		varA.setTheName("a");
		Variable varB = new Variable();
		varB.setTheName("b");
		Variable varC = new Variable();
		varC.setTheName("c");
		
		BinaryOp bop1 = new BinaryOp();
		bop1.setTheLHS(varB);
		bop1.setTheRHS(varC);
		bop1.setTheBinaryOperator(BinaryOperator.OR);
		
		BinaryOp bop2 = new BinaryOp();
		bop2.setTheLHS(varA);
		bop2.setTheRHS(bop1);	
		bop2.setTheBinaryOperator(BinaryOperator.EQUIV);
		
		
		return bop2;
	}
	
	// a <=> (notB)
	public static Expression getExample4(){
		Variable varA = new Variable();
		varA.setTheName("a");
		Variable varB = new Variable();
		varB.setTheName("b");
		
		BinaryOp bop1 = new BinaryOp();
		bop1.setTheLHS(varA);
		
		UnaryOp uop1 = new UnaryOp();
		uop1.setTheExpression(varB);
		uop1.setTheOperator(UnaryOperator.NOT);
		
		bop1.setTheRHS(uop1);
		bop1.setTheBinaryOperator(BinaryOperator.EQUIV);
		return bop1;
	}
	
	// a <=> (B)
	public static Expression getExample5(){
		Variable varA = new Variable();
		varA.setTheName("a");
		Variable varB = new Variable();
		varB.setTheName("b");
		
		BinaryOp bop1 = new BinaryOp();
		bop1.setTheLHS(varA);
		bop1.setTheRHS(varB);
		bop1.setTheBinaryOperator(BinaryOperator.EQUIV);
		
		return bop1;
	}
	

	// a => (b)
	public static BinaryOp getExample6(){
		Variable varA = new Variable();
		varA.setTheName("a");
		Variable varB = new Variable();
		varB.setTheName("b");
		
		BinaryOp bop1 = new BinaryOp();
		bop1.setTheLHS(varA);
		bop1.setTheBinaryOperator(BinaryOperator.IMP);
		bop1.setTheRHS(varB);
			
		
		return bop1;
	}
	
	public static Expression getImpExample0()
	{
		return EF.createVariable("a");
	}
	
	public static Expression getImpExample1()
	{
		return EF.createNot("a");
	}
	
	public static Expression getImpExample2(){
		return EF.createNot(EF.createEquiv("a", "b"));
	}
	
	
	public static Expression getImpExample3(){
		return EF.createNot(EF.createImp("a", "b"));
	}
	
	public static Expression getImpExample4(){
		return EF.createEquiv(EF.createImp("a", "b"), EF.createAnd("c", "d"));
	}
	
	public static Expression getImpExample5(){
		return EF.createNot(EF.createImp(EF.createOr("a", "b"), EF.createEquiv("c", "d")));
	}
	
	
	
	public static Expression getNotExample0(){
		return EF.createVariable("a");
	}
	
	public static Expression getNotExample1(){
		return EF.createNot("a");
	}
	
	public static Expression getNotExample2(){
		return EF.createNot(EF.createNot("a"));
	}
	
	public static Expression getNotExample3(){
		return EF.createAnd("a", "b");
	}
	
	public static Expression getNotExample4(){
		return EF.createNot(getNotExample3());
	}
	
	public static Expression getNotExample5(){
		return EF.createNot(EF.createOr("a", "b"));
	}
	
	public static Expression getNotExample6(){
		return EF.createNot(EF.createOr(EF.createNot("a"), "b"));
	}
	
	public static Expression getNotExample7(){
		return EF.createNot(EF.createOr(EF.createNot(EF.createNot("a")), "b"));
	}
	
	public static Expression getNotExample8(){
		return EF.createNot(EF.createOr(EF.createNot(EF.createNot("a")), EF.createNot("b")));
	}
	
	public static Expression getNotExample9(){
		return EF.createNot(EF.createOr(EF.createNot(EF.createNot("a")), getNotExample7()));
	}
}