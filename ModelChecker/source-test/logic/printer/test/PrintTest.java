package logic.printer.test;

import org.junit.Test;

import logic.model.Visitor;
import logic.printer.PrintVisitor;
import logic.test.examples.Examples;

public class PrintTest {
	
	@Test
	public void testPrintExample1(){
		StringBuilder b = new StringBuilder();
		PrintVisitor v = new PrintVisitor(b);
		v.visit(Examples.getExample1());
		System.out.println(b.toString());
	}
}
