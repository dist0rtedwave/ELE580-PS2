package logic.executor.test;

import java.io.IOException;

import org.junit.Test;

import logic.executor.MiniSat;
import logic.printer.DimacsPrinter;
import logic.test.examples.Examples;

public class MiniSatTest {
	
	@Test
	public  void testPrintExample1() throws IOException{
		MiniSat.exec();
		//System.out.println(DimacsPrinter.expressionToString(Examples.getExample1()));
	}
}
