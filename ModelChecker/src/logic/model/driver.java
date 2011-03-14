package logic.model;

public class driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
		
		
		
		Visitor vis0 = extracted(topLevelExpr);
		vis0.visit(topLevelExpr);
		System.out.println("\n");

	}

	private static Visitor<BinaryOp> extracted(BinaryOp bop1) {
		return new Visitor(bop1);
	}

}
