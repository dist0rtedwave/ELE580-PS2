
package logic.test.examples;

import logic.model.BinaryOp;
import logic.model.BinaryOperator;
import logic.model.EF;
import logic.model.Expression;
import logic.model.UnaryOp;
import logic.model.UnaryOperator;
import logic.model.Variable;

public class Examples {
	
	
	//  A/\B || C/\~D
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
	
	// UNSAT ((A \/ B) \/ (C \/ ~B)) /\ (~C /\ ~A)  
	public static BinaryOp getExample7()
	{
		Variable varA = new Variable();
		varA.setTheName("a");
		Variable varB = new Variable();
		varB.setTheName("b");
		Variable varC = new Variable();
		varC.setTheName("c");
		
		BinaryOp aorb = new BinaryOp();
		aorb.setTheLHS(varB);
		aorb.setTheRHS(varA);
		aorb.setTheBinaryOperator(BinaryOperator.OR);
  
		BinaryOp cornb = new BinaryOp();
		UnaryOp nb = new UnaryOp();
		nb.setTheExpression(varB);
		nb.setTheOperator(UnaryOperator.NOT);
		cornb.setTheLHS(nb);
		cornb.setTheRHS(varC);
		cornb.setTheBinaryOperator(BinaryOperator.OR);
	
		BinaryOp cora = new BinaryOp();
		cora.setTheLHS(aorb);
		cora.setTheRHS(cornb);
		cora.setTheBinaryOperator(BinaryOperator.AND);
		
		BinaryOp ncandna = new BinaryOp();
		UnaryOp na = new UnaryOp();
		na.setTheExpression(varA);
		na.setTheOperator(UnaryOperator.NOT);
		UnaryOp nc = new UnaryOp();
		nc.setTheExpression(varC);
		nc.setTheOperator(UnaryOperator.NOT);
        ncandna.setTheLHS(na);
        ncandna.setTheRHS(nc);
        ncandna.setTheBinaryOperator(BinaryOperator.AND);
        
        BinaryOp top = new BinaryOp();
        top.setTheLHS(ncandna);
        top.setTheRHS(cora);
        top.setTheBinaryOperator(BinaryOperator.AND);
        return top;
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
	
	public static Expression getNotExample10(){
		return EF.createAnd("z",EF.createNot(EF.createNot("a")));
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
	
	public static Expression getTotalExample0(){
		return EF.createNot(EF.createImp(EF.createNot(EF.createOr("z", EF.createOr("a", "b"))), EF.createEquiv("c", "d")));
	}
	
	public static Expression getTotalExample1(){
		return EF.createImp("a", "b");
	}
	
	public static Expression getTotalExample2(){
		return EF.createNot(EF.createEquiv("a", "b"));
	}
	
	public static Expression getTotalExample3(){
		return EF.createNot(EF.createOr(EF.createAnd("a", "b"), EF.createAnd("c","d")));
	}
	
	public static Expression getTotalExample4(){
		return EF.createImp(EF.createOr(EF.createAnd("a", "b"), EF.createAnd("c","d")),EF.createNot("z"));
	}
	
	public static Expression getTotalExample5(){
		return EF.createAnd("a", EF.createNot("a"));
	}
	
	public static Expression getTotalExample6(){
		return EF.createEquiv("a", EF.createNot("a"));
	}
	
	public static Expression getTotalExample7(){
		return EF.createNot(EF.createAnd(EF.createOr("x",EF.createOr(EF.createNot("y"), EF.createNot("z"))),EF.createOr("x",EF.createOr(EF.createNot("y"), EF.createNot("w")))));
	}
	
	public static Expression getNotExample11(){
		return EF.createAnd(EF.createNot(EF.createNot(EF.createVariable("1"))), EF.createNot(EF.createFalseLiteral()));
	}
	
	public static Expression getLiteralExample1(){
		return EF.createAnd(EF.createTrueLiteral(), EF.createTrueLiteral());
	}
	
	public static Expression getLiteralExample2(){
		return EF.createAnd(EF.createFalseLiteral(), EF.createFalseLiteral());
	}
	
	public static Expression getLiteralExample3(){
		return EF.createAnd(EF.createFalseLiteral(), EF.createTrueLiteral());
	}
	
	public static Expression getLiteralExample4(){
		return EF.createOr(EF.createFalseLiteral(), EF.createFalseLiteral());
	}
	
	public static Expression getLiteralExample5(){
		return EF.createOr(EF.createTrueLiteral(), EF.createFalseLiteral());
	}
	
	public static Expression getLiteralExample6(){
		return EF.createOr(EF.createTrueLiteral(), EF.createTrueLiteral());
	}
	
	public static Expression getLiteralExample7(){
		return EF.createAnd(EF.createVariable("x"), EF.createFalseLiteral());
	}
	
	public static Expression getLiteralExample8(){
		return EF.createAnd(EF.createVariable("x"), EF.createTrueLiteral());
	}
	
	public static Expression getLiteralExample9(){
		return EF.createAnd(EF.createTrueLiteral(), EF.createVariable("x"));
	}
	
	public static Expression getLiteralExample10(){
		return EF.createAnd(EF.createFalseLiteral(), EF.createVariable("x"));
	}
	
	public static Expression getLiteralExample11(){
		return EF.createOr(EF.createVariable("x"), EF.createFalseLiteral());
	}
	
	public static Expression getLiteralExample12(){
		return EF.createOr(EF.createVariable("x"), EF.createTrueLiteral());
	}
	
	public static Expression getLiteralExample13(){
		return EF.createOr(EF.createTrueLiteral(), EF.createVariable("x"));
	}
	
	public static Expression getLiteralExample14(){
		return EF.createOr(EF.createFalseLiteral(), EF.createVariable("x"));
	}
}