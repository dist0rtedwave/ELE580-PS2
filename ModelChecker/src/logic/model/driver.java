package logic.model;

public class driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		
		
		Visitor vis0 = extracted(topLevelExpr);
		vis0.visit(topLevelExpr, true);
		System.out.println("\n");

	}

	private static Visitor<BinaryOp> extracted(BinaryOp bop1) {
		return new Visitor(bop1);
	}

}
