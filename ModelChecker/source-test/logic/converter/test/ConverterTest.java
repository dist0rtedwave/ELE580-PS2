package logic.converter.test;

import org.junit.Test;

import logic.converter.Converter;
import logic.decoder.Tseitin2CNFDecoder;
import logic.model.Expression;
import logic.printer.PrintVisitor;
import logic.test.examples.Examples;

public class ConverterTest {
	
	@Test
	public void testConvertEx1(){
		Expression converted = Converter.convert(Examples.getExample1());
		System.out.println(PrintVisitor.expressionToString(converted));
		Expression decoded = Tseitin2CNFDecoder.decode(converted);
		
		System.out.println(PrintVisitor.expressionToString(decoded));
	}
	
}
