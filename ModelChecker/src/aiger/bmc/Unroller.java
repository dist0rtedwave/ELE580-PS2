package aiger.bmc;

import logic.model.EF;
import logic.printer.PrintVisitor;
import aiger.model.AigerFile;
import aiger.model.Expression;
import aiger.model.Latch;

public class Unroller {
// iterates over latches, calls clone on each. When done updates state
// then calls class to create CNF
	public static void unrollOnce(AigerFile af){
		for(Latch l : af.getTheLatches()){
			Cloner.clone(l);
		}
		for(Latch l : af.getTheLatches()){
			l.setTheCurrentState(l.getTheInterimState());
		}
	}
	
	public static void unroll(AigerFile af, int k){
		logic.model.Expression result=null;
		for(int i=0; i<k; i++){
//			System.out.println("k=" + i);
			for(Expression e : af.getTheOutputs()){
				logic.model.Expression lexp = CNFTranslator.CNFTranslate(e);
				if(result==null){
					result = lexp;
				}
				else{
					result=EF.createOr(result, lexp);
				}
//				System.out.println(PrintVisitor.expressionToString(lexp) + "\n\n");
			}
			unrollOnce(af);
		}
		System.out.println(PrintVisitor.expressionToString(result));
	}
}
