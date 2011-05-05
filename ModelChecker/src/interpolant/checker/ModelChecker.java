package interpolant.checker;

import interpolant.model.InterpolantBuilder;

import java.io.File;
import java.io.IOException;

import logic.converter.DeepCopy;
import logic.converter.NotDistributor;
import logic.executor.MiniSat;
import logic.model.EF;
import logic.model.Expression;
import logic.model.FalseLiteral;
import logic.printer.PrintVisitor;
import proof.model.Proof;
import aiger.bmc.CNFTranslator;
import aiger.bmc.Unroller;
import aiger.model.AigerFile;
import aiger.parser.AigerParser;

public class ModelChecker {
	
	public static boolean isSat(Expression e)
	{
		MiniSat ms = new MiniSat();
		boolean res = true;
		try {
			res = ms.execPrepared(e);
		} catch (IOException except) {
			except.printStackTrace();
		}
		// System.out.println(PrintVisitor.expressionToString(Full));
		return res;
	}
	
	
	public static Expression createNewInterpExpr(Expression orig, Expression interpolant)
	{
		return EF.createOr(interpolant, orig);
	}
	
	public static void runChecker(File file){
		AigerFile aigerFile = AigerParser.parseFile(file);
		int i = 0;
		//i = 0
	    Expression a0 = CNFTranslator.CNFTranslate((aiger.model.Expression) aigerFile.getTheOutputs().toArray()[0], i*aigerFile.getTheMaxVariable());
	    Unroller.unrollOnce(aigerFile);
	    i++;
	    
	    Expression a1 = CNFTranslator.CNFTranslate((aiger.model.Expression) aigerFile.getTheOutputs().toArray()[0], i*aigerFile.getTheMaxVariable());
	    
	    Unroller.unrollOnce(aigerFile);
	    //now we're at i = 2;e
	    
	    i = 2;
	    Expression b = EF.createTrueLiteral();

		while(true)
		{
			Expression a = DeepCopy.deepCopy(EF.createAnd(a0, a1));
			a = MiniSat.convertExpression(a);
			
			Expression bTrans = CNFTranslator.CNFTranslate((aiger.model.Expression) aigerFile.getTheOutputs().toArray()[0], i*aigerFile.getTheMaxVariable());
			bTrans = MiniSat.convertExpression(bTrans);
			b = EF.createAnd(b, bTrans);
			
			
		
			System.out.println(PrintVisitor.expressionToString(b));
			Expression interpolant = InterpolantBuilder.buildInterpolant(a,b);
			if(interpolant == null)
			{
				System.out.println("Not OK!");
				break;
			}
			if(interpolant.getDescriptor() == FalseLiteral.DESCRIPTOR)
			{
				System.out.println("Trivial!");
				break;
			}
			
			
			Expression a0_OR_interp = EF.createOr(DeepCopy.deepCopy(a0), interpolant);
			Expression a0_OR_interp_AND_a1 = EF.createAnd(DeepCopy.deepCopy(a1), a0_OR_interp);
			a0_OR_interp_AND_a1 =  MiniSat.convertExpression(a0_OR_interp_AND_a1);
			if(isSat(EF.createAnd(a0_OR_interp_AND_a1, b)))
				i++;
			else
			{
				System.out.println("Unsat! OK!");
				break;
			}
		}
	}
}
