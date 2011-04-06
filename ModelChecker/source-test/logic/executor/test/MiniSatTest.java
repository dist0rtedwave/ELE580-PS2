package logic.executor.test;

import java.io.IOException;

import org.junit.Test;

import logic.executor.MiniSat;
import logic.printer.DimacsPrinter;
import logic.test.examples.Examples;

public class MiniSatTest {
	
	@Test
	public  void testPrintExample1() throws IOException{
		MiniSat ms = new MiniSat();
		Boolean ret = ms.exec(Examples.getExample1());
		assert(ret==true);
	}
	
	@Test
	public  void testUnsatExample() throws IOException{
		MiniSat ms = new MiniSat();
		Boolean ret = ms.exec(Examples.getExample7());
		assert(ret==false);
	}
}
