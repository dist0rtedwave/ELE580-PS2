package logic.printer.test;

import logic.converter.ImpRemover;
import logic.decoder.Tseitin2CNFDecoder;
import logic.executor.MiniSat;
import logic.model.BinaryOp;
import logic.printer.DimacsPrinter;
import logic.test.examples.Examples;

import org.junit.Test;

public class DimacsTest {
	
	@Test
	public  void testPrintExample1(){
		DimacsPrinter dp = new DimacsPrinter(new StringBuilder());
		System.out.println(dp.expressionToString(Examples.getExample1()));
	}
	
	@Test
	public  void testPrintExample2(){
		DimacsPrinter dp = new DimacsPrinter(new StringBuilder());
		System.out.println(dp.expressionToString(Examples.getExample2()));
	}


	
	@Test // tests for regular equiv decoding i.e. a<=>b)
	public  void testEquivRemover1(){
		BinaryOp op = Examples.getExample3(); 
		ImpRemover.removeImps(op);
		DimacsPrinter dp = new DimacsPrinter(new StringBuilder());
		System.out.println(dp.expressionToString(op));
	}
	
	@Test // tests for regular equiv decoding i.e. a=>b)
	public  void testTotalExample0(){	
		DimacsPrinter dp = new DimacsPrinter(new StringBuilder());
		System.out.println(dp.expressionToString(MiniSat.convertExpression((Examples.getTotalExample0()))));
	}
	
	@Test
	public  void testTotalExample1(){
		DimacsPrinter dp = new DimacsPrinter(new StringBuilder());
		System.out.println(dp.expressionToString(MiniSat.convertExpression((Examples.getTotalExample1()))));
	}
	
	@Test
	public  void testTotalExample2(){
		DimacsPrinter dp = new DimacsPrinter(new StringBuilder());
		System.out.println(dp.expressionToString(MiniSat.convertExpression((Examples.getTotalExample2()))));
	}
	
	@Test
	public  void testTotalExample3(){
		DimacsPrinter dp = new DimacsPrinter(new StringBuilder());
		System.out.println(dp.expressionToString(MiniSat.convertExpression((Examples.getTotalExample3()))));
	}
	
	@Test
	public  void testTotalExample4(){	
		DimacsPrinter dp = new DimacsPrinter(new StringBuilder());
		System.out.println(dp.expressionToString(MiniSat.convertExpression((Examples.getTotalExample4()))));
	}
	
	
	
	
	
	
	

}
