package logic.converter.test;

import logic.converter.ImpRemover;
import logic.model.Expression;
import logic.printer.PrintVisitor;
import logic.test.examples.Examples;

import org.junit.Test;

public class ImpRemoverTest{
	
	private void testImp(Expression e){
		System.out.println(PrintVisitor.expressionToString(e) + "\n=");
		Expression remImp = ImpRemover.removeImps(e);
		System.out.println(PrintVisitor.expressionToString(remImp) +"\n\n");
	}
	
	@Test
	public void test0(){
		testImp(Examples.getImpExample0());
	}
	
	@Test
	public void test1(){
		testImp(Examples.getImpExample1());
	}
	
	@Test
	public void test2(){
		testImp(Examples.getImpExample2());
	}
	
	@Test
	public void test3(){
		testImp(Examples.getImpExample3());
	}
	
	@Test
	public void test4(){
		testImp(Examples.getImpExample4());
	}
	
	@Test
	public void test5(){
		testImp(Examples.getImpExample5());
	}
	

	
}