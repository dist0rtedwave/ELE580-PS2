package logic.converter.test;

import org.junit.Test;

import logic.converter.LiteralSimplifier;
import logic.converter.NotDistributor;
import logic.model.Expression;
import logic.printer.PrintVisitor;
import logic.test.examples.Examples;

public class LiteralSimplifierTest {

	private void testLS(Expression e){
		System.out.println(PrintVisitor.expressionToString(e) + "\n=");
		Expression ls = LiteralSimplifier.simplifyLiterals(e);
		System.out.println(PrintVisitor.expressionToString(ls) +"\n\n");
	}

	@Test
	public void testLS1(){
		testLS(Examples.getLiteralExample1());
	}
	
	@Test
	public void testLS2(){
		testLS(Examples.getLiteralExample2());
	}
	
	@Test
	public void testLS3(){
		testLS(Examples.getLiteralExample3());
	}
	
	@Test
	public void testLS4(){
		testLS(Examples.getLiteralExample4());
	}
	
	@Test
	public void testLS5(){
		testLS(Examples.getLiteralExample5());
	}
	
	@Test
	public void testLS6(){
		testLS(Examples.getLiteralExample6());
	}
	
	@Test
	public void testLS7(){
		testLS(Examples.getLiteralExample7());
	}
	
	@Test
	public void testLS8(){
		testLS(Examples.getLiteralExample8());
	}
	
	@Test
	public void testLS9(){
		testLS(Examples.getLiteralExample9());
	}
	
	@Test
	public void testLS10(){
		testLS(Examples.getLiteralExample10());
	}
	
	@Test
	public void testLS11(){
		testLS(Examples.getLiteralExample11());
	}
	
}
