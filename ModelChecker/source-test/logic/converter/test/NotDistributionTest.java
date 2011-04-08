package logic.converter.test;

import org.junit.Test;

import logic.converter.NotDistributor;
import logic.executor.MiniSat;
import logic.model.Expression;
import logic.printer.PrintVisitor;
import logic.test.examples.Examples;

public class NotDistributionTest{
	
	private void testNot(Expression e){
		System.out.println(PrintVisitor.expressionToString(e) + "\n=");
		Expression notDist = NotDistributor.distributeNots(e);
		System.out.println(PrintVisitor.expressionToString(notDist) +"\n\n");
	}
	
	@Test
	public void test0(){
		testNot(Examples.getNotExample0());
	}
	
	@Test
	public void test1(){
		testNot(Examples.getNotExample1());
	}
	
	@Test
	public void test2(){
		testNot(Examples.getNotExample2());
	}
	
	@Test
	public void test3(){
		testNot(Examples.getNotExample3());
	}
	
	@Test
	public void test4(){
		testNot(Examples.getNotExample4());
	}
	
	@Test
	public void test5(){
		testNot(Examples.getNotExample5());
	}
	
	@Test
	public void test6(){
		testNot(Examples.getNotExample6());
	}
	
	@Test
	public void test7(){
		testNot(Examples.getNotExample7());
	}
	
	@Test
	public void test8(){
		testNot(Examples.getNotExample8());
	}
	
	@Test
	public void test9(){
		testNot(Examples.getNotExample9());
	}
	
	@Test
	public void test10(){
		testNot(Examples.getNotExample10());
	}
	
	@Test
	public void test11(){
		testNot(MiniSat.convertExpression(Examples.getExample1()));
	}
	
	@Test
	public void test12(){
		testNot(Examples.getNotExample11());
	}
	
	@Test
	public void test13(){
		testNot(Examples.getNotExample12());
	}
	
	@Test
	public void test14(){
		testNot(Examples.getNotExample13());
	}
	
	
	
}