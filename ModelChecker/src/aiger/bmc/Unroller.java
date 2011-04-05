package aiger.bmc;

import java.io.FileOutputStream;
import java.io.PrintStream;

import logic.converter.LiteralSimplifier;
import logic.converter.NotDistributor;
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
        FileOutputStream out; // declare a file output object
        PrintStream p = null; // declare a print stream object
		try
        {
			out = new FileOutputStream("cnfout.txt");
			// Connect print stream to the output stream
               p = new PrintStream( out );
        }
        catch (Exception e)
        {
             System.err.println ("Error writing to file");
        }
        p.println(PrintVisitor.expressionToString(LiteralSimplifier.simplifyLiterals(NotDistributor.distributeNots(result))));
		//System.pr.println(PrintVisitor.expressionToString(result));
	}
}
