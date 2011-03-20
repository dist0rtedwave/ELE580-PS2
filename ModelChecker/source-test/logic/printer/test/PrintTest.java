package logic.printer.test;

import logic.decoder.Tseitin2CNFDecoder;
import logic.model.BinaryOp;
import logic.printer.PrintVisitor;
import logic.test.examples.Examples;

import org.junit.Test;

public class PrintTest {
	
	@Test
	public  void testPrintExample1(){
		System.out.println(PrintVisitor.expressionToString(Examples.getExample1()));
	}
	
	@Test
	public  void testPrintExample2(){
		System.out.println(PrintVisitor.expressionToString(Examples.getExample2()));
	}
	
	
	
	
	

}
