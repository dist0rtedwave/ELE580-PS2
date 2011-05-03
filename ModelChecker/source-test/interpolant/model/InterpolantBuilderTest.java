package interpolant.model;

import java.io.IOException;

import logic.converter.ImpRemover;
import logic.converter.NotDistributor;
import logic.decoder.Tseitin2CNFDecoder;
import logic.executor.MiniSat;
import logic.model.*;
import logic.printer.DimacsPrinter;
import logic.printer.PrintVisitor;
import logic.test.examples.Examples;

import org.junit.Test;

import proof.model.NamePrinterTraverser;
import proof.model.Proof;

public class InterpolantBuilderTest {
	
	@Test
	public  void testInterpolant1() throws IOException{
		
		Expression A = EF.createAnd(EF.createNot("a1"), EF.createOr("a1", EF.createNot("a2")));
		Expression B = EF.createAnd(EF.createOr(EF.createNot("a1"), "a2"), "a1");
		
		A = MiniSat.convertExpression(A);
		B = MiniSat.convertExpression(B);
		
		Expression Full = EF.createAnd(A, B);
		
		System.out.println(PrintVisitor.expressionToString(A));	
		System.out.println(PrintVisitor.expressionToString(B));	
		
		//System.out.println(PrintVisitor.expressionToString(Full));	
		
		MiniSat ms = new MiniSat();
		boolean res = ms.execPrepared(Full);
		//System.out.println(PrintVisitor.expressionToString(Full));
		if(!res)
		{
			Proof p = new Proof();
			p.load("/tmp/proof");
			//NamePrinterTraverser npt = new NamePrinterTraverser();
			//npt.setNameMap(ms.getNameMap());
			//p.traverse(npt);
			
			//System.out.println(PrintVisitor.expressionToString(Full));	
            //System.out.println(PrintVisitor.expressionToString(A));			
            //System.out.println(PrintVisitor.expressionToString(B));
            
			InterpolantBuilder ib = new InterpolantBuilder();
			ib.setNameMap(ms.getNameMap());
			ib.setAExpression(A);
			ib.setBExpression(B);
			Expression interp = ib.getInterpolant(p);
			System.out.println(PrintVisitor.expressionToString(interp));
			
			//Expression last = ib.partialInterpolants.get(p.getLastClause());
			//System.out.println(PrintVisitor.expressionToString(last));
			
		}
	}
	
}
