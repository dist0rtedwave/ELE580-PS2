package logic.printer.test;

import org.junit.Test;

import logic.converter.ImpRemover;
import logic.decoder.Tseitin2CNFDecoder;
import logic.model.BinaryOp;
import logic.model.Expression;
import logic.model.Visitor;
import logic.printer.DimacsPrinter;
import logic.test.examples.Examples;

public class DimacsTest {
	
	@Test
	public  void testPrintExample1(){
		System.out.println(DimacsPrinter.expressionToString(Examples.getExample1()));
	}
	
	@Test
	public  void testPrintExample2(){
		System.out.println(DimacsPrinter.expressionToString(Examples.getExample2()));
	}
	
	@Test // tests for conjunctive decoding i.e. a<=>(b/\c)
	public  void testTseitinConjunctive(){
		BinaryOp tseitinExpr = new BinaryOp(); 	// will contain the tseitin expression after decode is runs
		Tseitin2CNFDecoder dc = new Tseitin2CNFDecoder(tseitinExpr);
		dc.visit(Examples.getExample2());
		System.out.println(DimacsPrinter.expressionToString(tseitinExpr));
	}
	
	@Test // tests for disjunctive decoding i.e. a<=>(b\/c)
	public  void testTseitinDisjunctive(){
		BinaryOp tseitinExpr = new BinaryOp(); 	// will contain the tseitin expression after decode is runs
		Tseitin2CNFDecoder dc = new Tseitin2CNFDecoder(tseitinExpr);
		dc.visit(Examples.getExample3());
		System.out.println(DimacsPrinter.expressionToString(tseitinExpr));
	}
	
	@Test // tests for negation decoding i.e. a<=>(notB)
	public  void testTseitinNegation(){
		BinaryOp tseitinExpr = new BinaryOp(); 	// will contain the tseitin expression after decode is runs
		Tseitin2CNFDecoder dc = new Tseitin2CNFDecoder(tseitinExpr);
		dc.visit(Examples.getExample4());
		System.out.println(DimacsPrinter.expressionToString(tseitinExpr));
	}
	
	@Test // tests for regular equiv decoding i.e. a<=>b)
	public  void testTseitinEquiv(){
		BinaryOp tseitinExpr =  new BinaryOp(); 	// will contain the tseitin expression after decode is runs
		Tseitin2CNFDecoder dc = new Tseitin2CNFDecoder(tseitinExpr);
		dc.visit(Examples.getExample5());
		System.out.println(DimacsPrinter.expressionToString(tseitinExpr));
	}
	
	@Test // tests for regular equiv decoding i.e. a<=>b)
	public  void testEquivRemover1(){
		BinaryOp op = Examples.getExample3(); 
		ImpRemover.convert(op);
		System.out.println(DimacsPrinter.expressionToString(op));
	}
	
	@Test // tests for regular equiv decoding i.e. a=>b)
	public  void testImpRemover1(){
		BinaryOp op = Examples.getExample6(); 
		ImpRemover.convert(op);
		System.out.println(DimacsPrinter.expressionToString(op));
	}
	
	
	
	

}
