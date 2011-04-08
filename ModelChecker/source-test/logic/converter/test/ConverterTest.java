package logic.converter.test;

import logic.converter.Converter;
import logic.model.EF;
import logic.model.Expression;
import logic.printer.PrintVisitor;
import logic.test.examples.Examples;

import org.junit.Test;

public class ConverterTest {
	
	private void testConvert(Expression e){
		System.out.println(PrintVisitor.expressionToString(e) + "\n=");
		Expression decode = Converter.convert(e);
		System.out.println(PrintVisitor.expressionToString(decode) +"\n\n");
	}
	
	@Test
	public void test0(){
		testConvert(Examples.getExample1());
	}
	
	@Test
	public void test1(){
		testConvert(Examples.getExample2());
	}
	
	@Test
	public void test2(){
		testConvert(Examples.getExample3());
	}
	
	@Test
	public void test3(){
		testConvert(Examples.getExample4());
	}
	
	@Test
	public void test4(){
		testConvert(Examples.getExample5());
	}
	
	@Test
	public void test5(){
		testConvert(Examples.getExample6());
	}
	
	@Test
	public void test6(){
		testConvert(EF.createOr(EF.createOr(EF.createFalseLiteral(), EF.createFalseLiteral()), EF.createFalseLiteral()));
	}
}
