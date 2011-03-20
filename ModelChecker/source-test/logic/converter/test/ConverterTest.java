package logic.converter.test;

import org.junit.Test;

import logic.decoder.Tseitin2CNFDecoder;
import logic.model.Expression;
import logic.printer.PrintVisitor;
import logic.test.examples.Examples;

public class ConverterTest {
	
	private void testConvert(Expression e){
		System.out.println(PrintVisitor.expressionToString(e) + "\n=");
		Expression decode = Tseitin2CNFDecoder.decode(e);
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
}
