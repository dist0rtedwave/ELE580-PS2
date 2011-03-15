package logic.printer.test;

import org.junit.Test;

import logic.decoder.Tseitin2CNFDecoder;
import logic.model.BinaryOp;
import logic.model.Expression;
import logic.model.Visitor;
import logic.printer.PrintVisitor;
import logic.test.examples.Examples;

public class PrintTest {
	
	@Test
	public  void testPrintExample1(){
		StringBuilder b = new StringBuilder();
		PrintVisitor v = new PrintVisitor(b);
		v.visit(Examples.getExample1());
		System.out.println(b.toString());
	}
	
	@Test
	public  void testPrintExample2(){
		StringBuilder b = new StringBuilder();
		PrintVisitor v = new PrintVisitor(b);
		v.visit(Examples.getExample2());
		System.out.println(b.toString());
	}
	
	@Test // tests for conjunctive decoding i.e. a<=>(b/\c)
	public  void testTseitinConjunctive(){
		StringBuilder b = new StringBuilder();
		PrintVisitor v = new PrintVisitor(b);
		
		BinaryOp tseitinExpr = new BinaryOp(); 	// will contain the tseitin expression after decode is runs
		Tseitin2CNFDecoder dc = new Tseitin2CNFDecoder(tseitinExpr);
		dc.visit(Examples.getExample2());
		
		v.visit(tseitinExpr);
		System.out.println(b.toString());
	}
	
	@Test // tests for disjunctive decoding i.e. a<=>(b\/c)
	public  void testTseitinDisjunctive(){
		StringBuilder b = new StringBuilder();
		PrintVisitor v = new PrintVisitor(b);
		
		BinaryOp tseitinExpr =  new BinaryOp(); 	// will contain the tseitin expression after decode is runs
		Tseitin2CNFDecoder dc = new Tseitin2CNFDecoder(tseitinExpr);
		dc.visit(Examples.getExample3());
		
		v.visit(tseitinExpr);
		System.out.println(b.toString());
	}
	
	@Test // tests for negation decoding i.e. a<=>(notB)
	public  void testTseitinNegation(){
		
		StringBuilder b = new StringBuilder();
		PrintVisitor v = new PrintVisitor(b);
		
		BinaryOp tseitinExpr =  new BinaryOp(); 	// will contain the tseitin expression after decode is runs
		Tseitin2CNFDecoder dc = new Tseitin2CNFDecoder(tseitinExpr);
		dc.visit(Examples.getExample4());
		
		v.visit(tseitinExpr );
		
		System.out.println(b.toString());
		
	}
	
	@Test // tests for regular equiv decoding i.e. a<=>b)
	public  void testTseitinEquiv(){
		StringBuilder b = new StringBuilder();
		PrintVisitor v = new PrintVisitor(b);
		
		BinaryOp tseitinExpr =  new BinaryOp(); 	// will contain the tseitin expression after decode is runs
		Tseitin2CNFDecoder dc = new Tseitin2CNFDecoder(tseitinExpr);
		dc.visit(Examples.getExample5());
		
		v.visit(tseitinExpr);
		System.out.println(b.toString());
	}
	

}
