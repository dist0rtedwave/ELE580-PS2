package interpolant.model;

import java.io.IOException;

import logic.converter.ImpRemover;
import logic.converter.NotDistributor;
import logic.decoder.Tseitin2CNFDecoder;
import logic.executor.MiniSat;
import logic.model.*;
import logic.printer.DimacsPrinter;
import logic.test.examples.Examples;

import org.junit.Test;

import proof.model.NamePrinterTraverser;
import proof.model.Proof;

public class InterpolantBuilderTest {
	
	@Test
	public  void testInterpolant1() throws IOException{
		
		Expression A = EF.createAnd(EF.createNot("a1"), EF.createOr("a1", EF.createNot("a2")));
		Expression B = EF.createAnd(EF.createOr(EF.createNot("a1"), "a2"), "a1");
		
		Expression Full = EF.createAnd(A, B);
		
		MiniSat ms = new MiniSat();
		boolean res = ms.exec(Full);
		
		if(!res)
		{
			Proof p = new Proof();
			p.load("/tmp/proof");
			NamePrinterTraverser npt = new NamePrinterTraverser();
			npt.setNameMap(ms.getNameMap());
			p.traverse(npt);
		}
	}
	
}
