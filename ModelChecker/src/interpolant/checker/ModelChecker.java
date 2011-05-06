package interpolant.checker;

import interpolant.model.InterpolantBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import logic.converter.DeepCopy;
import logic.converter.ImpRemover;
import logic.converter.LiteralSimplifier;
import logic.converter.NotDistributor;
import logic.executor.MiniSat;
import logic.model.EF;
import logic.model.Expression;
import logic.model.FalseLiteral;
import logic.printer.PrintVisitor;
import util.Pair;
import aiger.bmc.CNFTranslator;
import aiger.bmc.Cloner;
import aiger.bmc.Unroller;
import aiger.model.AigerFile;
import aiger.model.Latch;
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
	
	public static HashMap<Latch, Integer> id2latch = new HashMap<Latch, Integer>();
	static int numLatches;
	public static Pair<Expression,Expression> addEquivs(AigerFile af, int time)
	{
		Pair<Expression,Expression> p = new Pair<Expression, Expression>(null, null);
		Expression equivs = EF.createTrueLiteral();
		numLatches = af.getTheLatches().size();
		for(Latch l : af.getTheLatches()){
			aiger.model.Expression latchState = l.getTheCurrentState();
			logic.model.Expression lexp = CNFTranslator.CNFTranslate(latchState);
		
			lexp = NotDistributor.distributeNots(lexp);
			int latchNum = id2latch.get(l);
			equivs = EF.createAnd(equivs, EF.createEquiv(EF.createVariable("_l"+latchNum+"_"+time), lexp));
			equivs = ImpRemover.removeImps(equivs);
			equivs = NotDistributor.distributeNots(equivs);
			equivs = LiteralSimplifier.simplifyLiterals(equivs);
			Cloner.clone(l);
		}
		p.setFirst(equivs);
		Expression convertedOuts = CNFTranslator.convertToLogicExpressionWithLatchesAsVariables((aiger.model.Expression) af.getTheOutputs().toArray()[0], time);
		p.setSecond(convertedOuts);
		
		for(Latch l : af.getTheLatches()){
			l.setTheCurrentState(l.getTheInterimState());
		}
		return p;
	}
	
	public static void runChecker(File file){
		AigerFile aigerFile = AigerParser.parseFile(file);
		int i = 0;
		
		int latchNum = 0;
		for(Latch l : aigerFile.getTheLatches())
		{
			id2latch.put(l, latchNum);
			latchNum++;
		}
		
		
	    Expression a0 = addEquivs(aigerFile, 0).first();
		
		//Expression a0 = CNFTranslator.CNFTranslate((aiger.model.Expression) aigerFile.getTheOutputs().toArray()[0], i*aigerFile.getTheMaxVariable());
	    //Unroller.unrollOnce(aigerFile);
	    
	   Expression a1 = addEquivs(aigerFile, 1).first();
	    // Expression a1 = CNFTranslator.CNFTranslate((aiger.model.Expression) aigerFile.getTheOutputs().toArray()[0], i*aigerFile.getTheMaxVariable());    
	    //Unroller.unrollOnce(aigerFile);
	    
	    i = 2;
	    Expression b = EF.createTrueLiteral();
		while(true)
		{
			Expression a0_and_a1 = EF.createAnd(a0, a1);
			Expression a = DeepCopy.deepCopy(a0_and_a1);
			a = MiniSat.convertExpression(a);
			/*
			if(isSat(a))
			{
				System.out.println("A is Satisfiable!");
			}
			else
			{
				System.out.println("Ais unsat!");
			}
			*/
			Pair<Expression,Expression> unrollResult = addEquivs(aigerFile, i);
			Expression bConstraints = unrollResult.first();
			Expression bOutput = EF.createNot(unrollResult.second());
			
			Expression newB = DeepCopy.deepCopy(EF.createAnd(bConstraints, bOutput));
			newB = MiniSat.convertExpression(newB);
			
			b = DeepCopy.deepCopy(EF.createAnd(b, newB));
			
		//	System.out.println(PrintVisitor.expressionToString(b));
			Expression interpolant = InterpolantBuilder.buildInterpolant(a,b);
			if(interpolant == null)
			{
				System.out.println("Not OK!");
				break;
			}
			if(interpolant.getDescriptor() == FalseLiteral.DESCRIPTOR)
			{
				System.out.println("Trivial!");
				System.out.println("The interpolant is :");
				System.out.println(PrintVisitor.expressionToString(interpolant));
				break;
			}
			

			interpolant = MiniSat.convertExpression(DeepCopy.deepCopy(interpolant));
			Expression a0_OR_interp = EF.createOr(DeepCopy.deepCopy(a0), interpolant);
			Expression a0_OR_interp_AND_a1 = DeepCopy.deepCopy(EF.createAnd(DeepCopy.deepCopy(a1), a0_OR_interp));
			a0_OR_interp_AND_a1 =  MiniSat.convertExpression(a0_OR_interp_AND_a1);
			if(isSat(EF.createAnd(a0_OR_interp_AND_a1, b)))
				i++;
			else
			{
				System.out.println("a0 is :");
				System.out.println(PrintVisitor.expressionToString(a0));
				System.out.println("a1 is :");
				System.out.println(PrintVisitor.expressionToString(a1));
				System.out.println("interpolant is :");
				System.out.println(PrintVisitor.expressionToString(interpolant));
				System.out.println("UNSAT! OK");
				break;
			}
		}
	}
}
